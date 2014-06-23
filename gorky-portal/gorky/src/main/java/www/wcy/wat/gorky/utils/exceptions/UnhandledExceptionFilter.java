package www.wcy.wat.gorky.utils.exceptions;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UnhandledExceptionFilter implements Filter
{
    @Override
    public void init( FilterConfig filterConfig ) throws ServletException
    {
    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse,
                          FilterChain filterChain ) throws IOException, ServletException
    {
        try
        {
            filterChain.doFilter( servletRequest, servletResponse );
        }
        catch ( Exception ex )
        {
            System.err.println( "Unhandled exception" );
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy()
    {
    }
}
