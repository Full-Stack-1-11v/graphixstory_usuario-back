import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.validator.constraints.ModCheck;
import org.springframework.beans.factory.annotation.Autowired;

import com.graphixstory.usuarios.model.Usuario;
import com.graphixstory.usuarios.service.UsuarioService;

@springframework
@ActiveProfiles("test")
public class UsuariosServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepo usuariorepositorio;

    @Test
    public void testGetUsuario() {

        //given
        List<Usuario> usuario = new ArrayList<>();

        //when 
        //when(usuariorepositorio) result = usuarioService.getAllUsuarios();
    }

}
