package br.edu.ifpi.datasusscraping.client;

import br.edu.ifpi.datasusscraping.domain.Equipamento;
import br.edu.ifpi.datasusscraping.domain.Estatistica;
import br.edu.ifpi.datasusscraping.domain.Municipio;
import br.edu.ifpi.datasusscraping.entity.EstatisticaScraping;
import br.edu.ifpi.datasusscraping.service.EquipamentoService;
import br.edu.ifpi.datasusscraping.service.EstatisticaService;
import br.edu.ifpi.datasusscraping.service.MunicipioService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataSUS {

    private final EstatisticaService estatisticaService;
    private final EquipamentoService equipamentoService;
    private final MunicipioService municipioService;
    private List<EstatisticaScraping> ultimoScraping = new ArrayList<>();

    public List<EstatisticaScraping> scrape(int municipio){
        try (WebClient webClient = new WebClient()) {
            HtmlPage page = webClient.getPage("http://cnes2.datasus.gov.br/Mod_Ind_Equipamento.asp?VEstado=22&VMun="+municipio);
            DomNodeList<DomElement> tbodyElements = page.getElementsByTagName("tbody");
            DomElement tbodyElement = tbodyElements.get(tbodyElements.getLength() - 1);
            DomNodeList<HtmlElement> trElements = tbodyElement.getElementsByTagName("tr");

            LinkedList<EstatisticaScraping> estatisticas = new LinkedList<>();
            for (HtmlElement tr : trElements) {
                if (tr.getAttribute("bgcolor").isEmpty()) {
                    continue;
                }

                HtmlElement fontElement = tr.getElementsByTagName("font").get(0);
                String textContent = fontElement.getTextContent().substring(2);

                EstatisticaScraping estatistica = new EstatisticaScraping();
                DomNodeList<HtmlElement> tdElements = tr.getElementsByTagName("td");

                Integer codigo = getAttributeFromFontTag(tdElements.get(0));
                estatistica.setCodigo(codigo.longValue());

                HtmlElement tdNome = tdElements.get(1);
                String nome = tdNome.getElementsByTagName("a").get(0).getTextContent();
                estatistica.setNome(nome);

                Integer existentes = getAttributeFromFontTag(tdElements.get(2));
                estatistica.setExistentes(existentes);

                Integer emUso = getAttributeFromFontTag(tdElements.get(3));
                estatistica.setEmUso(emUso);

                Integer existentesSUS = getAttributeFromFontTag(tdElements.get(4));
                estatistica.setExistentesSUS(existentesSUS);

                Integer emUsoSus = getAttributeFromFontTag(tdElements.get(5));
                estatistica.setEmUsoSus(emUsoSus);

                estatisticas.add(estatistica);
            }
            ultimoScraping.addAll(estatisticas);
            return estatisticas;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void sincronizarPorMunicipio(String municipio){
        Municipio municipioScraping = municipioService.findyByNome(municipio);
        int codMunicipio = br.edu.ifpi.datasusscraping.client.Municipio.valueOf(municipio.toUpperCase()).getValor();
        ultimoScraping = scrape(codMunicipio);

        estatisticaService.deleteByMunicipio(municipioScraping);

        for (EstatisticaScraping edto : ultimoScraping){
            Equipamento equipamento = Equipamento.builder()
                    .cod(edto.getCodigo())
                    .nome(edto.getNome())
                    .build();

            Equipamento newOrRefreshequipamento = equipamentoService.save(equipamento);

            Estatistica estatisticaObtida = Estatistica.builder()
                    .id(Long.parseLong(newOrRefreshequipamento.getCod().toString() + municipioScraping.getCod().toString()))
                    .equipamento(newOrRefreshequipamento)
                    .municipio(municipioScraping)
                    .emUso(edto.getEmUso())
                    .existentes(edto.getExistentes())
                    .emUsoSus(edto.getEmUsoSus())
                    .existentesSUS(edto.getExistentesSUS())
                    .build();

            estatisticaService.save(estatisticaObtida);
        }
    }

    private static Integer getAttributeFromFontTag(HtmlElement htmlElement){
        DomNodeList<HtmlElement> htmlElements = htmlElement.getElementsByTagName("font");
        HtmlElement firstHtmlElement = htmlElements.get(0);
        String textContent = firstHtmlElement.getTextContent();
        return Integer.parseInt(textContent);
    }

}
