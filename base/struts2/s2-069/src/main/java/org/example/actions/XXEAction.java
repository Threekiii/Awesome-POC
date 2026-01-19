package org.example.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import javax.servlet.http.HttpServletResponse;
import java.io.StringReader;
import java.io.PrintWriter;

public class XXEAction extends ActionSupport implements ServletResponseAware {

    private String xmlContent;
    private HttpServletResponse response;

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String execute() throws Exception {
        if (xmlContent != null && !xmlContent.trim().isEmpty()) {
            try {
                InputSource inputSource = new InputSource(new StringReader(xmlContent));
                Document doc = com.opensymphony.xwork2.util.DomHelper.parse(inputSource);
                String root = doc.getDocumentElement().getTagName();
                String textContent = doc.getDocumentElement().getTextContent();
                String result = "XML parsed. Root: " + root + "\nContent: " + textContent;
                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                out.write(result);
                out.flush();
                return NONE;
            } catch (Exception e) {
                String error = "Error: " + e.getMessage();
                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                out.write(error);
                out.flush();
                return NONE;
            }
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.write("No XML provided");
        out.flush();
        return NONE;
    }

    public String getXmlContent() {
        return xmlContent;
    }

    public void setXmlContent(String xmlContent) {
        this.xmlContent = xmlContent;
    }
}