package interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import util.TokenUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZAY
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if(token != null){
            boolean result = TokenUtil.verify(token);
            if(result){
                //通过拦截器
                return true;
            }
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            JSONObject json = new JSONObject();
            json.put("msg","token verify fail");
            json.put("code","50000");
            response.getWriter().append(json.toJSONString());
            //认证失败，未通过拦截器
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}
