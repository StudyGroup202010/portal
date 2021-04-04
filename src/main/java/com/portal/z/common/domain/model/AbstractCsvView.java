package com.portal.z.common.domain.model;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;

/**
 * CSVファイル出力用
 *
 */
public abstract class AbstractCsvView extends AbstractView {

    @Override
    protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());

        addResponseHeader(model, request, response);
        buildCsvDocument(model, request, response);

    }

    protected abstract void addResponseHeader(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response);

    protected abstract void buildCsvDocument(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
}
