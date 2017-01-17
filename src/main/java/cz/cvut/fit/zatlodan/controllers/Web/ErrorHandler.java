package cz.cvut.fit.zatlodan.controllers.Web;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by jack on 20.12.16.
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) throws Exception{
        ex.printStackTrace();

        if (AnnotationUtils.findAnnotation(ex.getClass(),ResponseStatus.class) != null){
            throw ex;
        }

        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));

        ModelAndView v = new ModelAndView("errors/internal-error");
        ModelMap model = v.getModelMap();
        model.put("exception", ex);
        model.put("url",  req.getRequestURL());
        model.put("errorMessage", ex.getMessage());
        model.put("basePath", req.getContextPath());
        model.put("stackTrace", errors.toString());
        return v;
    }
}
