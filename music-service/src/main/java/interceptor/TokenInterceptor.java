package interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.TokenUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZAY
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String token = request.getHeader("token");
        if(token != null){
            //通过拦截器
            if(TokenUtil.verify(token)){
                return true;
            }else {
                try{
                    //认证失败，未通过拦截器
                    JSONObject json = new JSONObject();
                    json.put("msg","token verify fail");
                    response.getWriter().append(json.toJSONString());
                }catch (Exception e){
                    e.printStackTrace();
                    response.sendError(500);
                    return false;
                }
            }
        }else {
            JSONObject json = new JSONObject();
            json.put("msg","token not found");
            response.getWriter().append(json.toJSONString());
        }
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
