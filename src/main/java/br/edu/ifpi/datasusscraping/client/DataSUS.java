package br.edu.ifpi.datasusscraping.client;

import br.edu.ifpi.datasusscraping.entity.Equipamento;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataSUS {
    public List<Equipamento> scrape(int municipio){
        try (WebClient webClient = new WebClient()) {
            HtmlPage page = webClient.getPage("http://cnes2.datasus.gov.br/Mod_Ind_Equipamento.asp?VEstado=22&VMun="+municipio);
            DomNodeList<DomElement> tbodyElements = page.getElementsByTagName("tbody");
            DomElement tbodyElement = tbodyElements.get(tbodyElements.getLength() - 1);
            DomNodeList<HtmlElement> trElements = tbodyElement.getElementsByTagName("tr");

            LinkedList<Equipamento> equipamentos = new LinkedList<>();
            for (HtmlElement tr : trElements) {
                if (tr.getAttribute("bgcolor").isEmpty()) {
                    continue;
                }

                HtmlElement fontElement = tr.getElementsByTagName("font").get(0);
                String textContent = fontElement.getTextContent().substring(2);

                Equipamento equipamento = new Equipamento();
                DomNodeList<HtmlElement> tdElements = tr.getElementsByTagName("td");

                Integer codigo = getAttributeFromFontTag(tdElements.get(0));
                equipamento.setId(codigo.longValue());

                HtmlElement tdNome = tdElements.get(1);
                String nome = tdNome.getElementsByTagName("a").get(0).getTextContent();
                equipamento.setNome(nome);

                Integer existentes = getAttributeFromFontTag(tdElements.get(2));
                equipamento.setExistentes(existentes);

                Integer emUso = getAttributeFromFontTag(tdElements.get(3));
                equipamento.setEmUso(emUso);

                Integer existentesSUS = getAttributeFromFontTag(tdElements.get(4));
                equipamento.setExistentesSUS(existentesSUS);

                Integer emUsoSus = getAttributeFromFontTag(tdElements.get(5));
                equipamento.setEmUsoSus(emUsoSus);

                equipamentos.add(equipamento);
            }
            return equipamentos;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static Integer getAttributeFromFontTag(HtmlElement htmlElement){
        DomNodeList<HtmlElement> htmlElements = htmlElement.getElementsByTagName("font");
        HtmlElement firstHtmlElement = htmlElements.get(0);
        String textContent = firstHtmlElement.getTextContent();
        return Integer.parseInt(textContent);
    }

}
