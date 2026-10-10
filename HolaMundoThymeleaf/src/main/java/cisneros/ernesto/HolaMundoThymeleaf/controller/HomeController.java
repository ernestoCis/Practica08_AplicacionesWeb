package cisneros.ernesto.HolaMundoThymeleaf.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@RequestParam(name = "nombre", required = false, defaultValue = "Invítado") String nombre, Model model, HttpSession session){
        model.addAttribute("mensajeBienvenida",
                "¡Hola mundo desde Spring Boot! :)");

        model.addAttribute("nombreUsuario", nombre);
        session.setAttribute("operador", "Admin");
        return "index";
    }

    @GetMapping("/saludo")
    public String saludo(Model model){
        LocalTime now = LocalTime.now();
        int hora = now.getHour();

        String saludo;
        String claseCss;

        if(hora>=5 && hora<12){
            saludo = "¡Buenos días!";
            claseCss = "color: #198754";
        }else if(hora>=12 && hora<19){
            saludo = "Buenas tardes";
            claseCss = "color: #0d6efd";
        }else{
            saludo = "Buenas noches";
            claseCss = "color: #dc3545";
        }
        model.addAttribute("saludo", saludo);
        model.addAttribute("claseCss", claseCss);

        return "saludo";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model){
        List<Map<String, Object>> juegos = new ArrayList<>();
        juegos.add(crearJuego("GTA 6", "Genero1", "Consolas", "20000", "true"));
        juegos.add(crearJuego("GTA 5", "Genero1", "Consolas", "500", "true"));
        juegos.add(crearJuego("GTA 4", "Genero1", "Consolas", "400", "true"));
        juegos.add(crearJuego("GTA San Andreas", "Genero1", "Consolas", "500", "false"));
        juegos.add(crearJuego("Fortnite", "Shooter", "Consolas", "0", "true"));

        model.addAttribute("juegos", juegos);

        return "catalogo";
    }

    // El método debe estar DENTRO de la clase HomeController
    private Map<String, Object> crearJuego(String titulo, String genero, String plataforma, String precio, String disponible){
        Map<String, Object> juego = new HashMap<>();

        juego.put("titulo", titulo);
        juego.put("genero", genero);
        juego.put("plataforma", plataforma);
        juego.put("precio", precio);
        juego.put("disponible", disponible);

        return juego;
    }
}