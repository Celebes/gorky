//
//package www.wcy.wat.gorky.controllers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import www.wcy.wat.gorky.dto.ExampleDTO;
//import www.wcy.wat.gorky.managers.ExampleManager;
//import www.wcy.wat.gorky.model.Example;
//import www.wcy.wat.gorky.repositories.ExampleRepository;
//
//@Controller
//@RequestMapping(value = "/")
//public class ExampleController
//{
//    @Autowired
//    private ExampleManager exampleManager;
//
//    @Autowired
//    private ExampleRepository exampleRepository;
//
//    @RequestMapping(value = "/example", method = RequestMethod.GET)
//    public String getExamplePage( Model model )
//    {
//        model.addAttribute( "exampleForm", new ExampleDTO() );
//        List<Example> cos = exampleRepository.findAll();
//        System.out.println("Poszly zmiany2!!!");
//        if(cos.isEmpty())
//        {
//        	System.out.println("Nie udało się nic znaleźć");
//        	
//        }else
//        {
//        	for(Example ex : cos)
//        	{
//        		System.out.println("Znalezniono: " + ex.getText());
//        	}
//        }
//        return "example/example";
//    }
//    
//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String getMainPage( Model model )
//    {
//    	model.addAttribute( "exampleForm", new ExampleDTO() );
//        return "index";
//    }
//
//    @RequestMapping(value = "/example", method = RequestMethod.POST)
//    public String processExamplForm( @Valid @ModelAttribute("exampleForm") ExampleDTO exampleDTO,
//                                     BindingResult bindingResult, Model model )
//    {
//        if ( !bindingResult.hasErrors() )
//        {
//            try
//            {
//                Example example = new Example( exampleDTO );
//                example = exampleManager.setExampleText( example, "test" );
//                exampleRepository.saveExample( example );
//                model.addAttribute( "feedback", "Cośtam poszło" );
//            }
//            catch ( DataAccessException e )
//            {
//                System.out.println( e.getLocalizedMessage() );
//            }
//        }
//        return "example/example";
//    }
//
//    @RequestMapping(value = "/tabela")
//    public String getTable( Model model )
//    {
//        List<ExampleDTO> list = new ArrayList<ExampleDTO>();
//        list.add( new ExampleDTO() );
//        list.add( new ExampleDTO() );
//        model.addAttribute( "lista", list );
//        model.addAttribute( "listaSize", 250 );
//
//        return "example/tabela";
//    }
//}
