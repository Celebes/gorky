//package www.wcy.wat.gorky.managers;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//
//import www.wcy.wat.gorky.model.Example;
//
//@Service
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class ExampleManager
//{
//    private int i = 1;
//
//    /**
//     * Metoda ustawia "text" klasy Example
//     *
//     * @param example - klasa Example do podmiany tekstu, tworzy nowa jesli null
//     * @param text    - tekst do wstawienia
//     * @return - uaktualniony obiekt
//     */
//    public Example setExampleText( Example example, String text )
//    {
//        i++;
//        if ( example == null )
//        {
//            example = new Example();
//        }
//        example.setText( text );
//        System.out.println( i );
//        return example;
//    }
//}
