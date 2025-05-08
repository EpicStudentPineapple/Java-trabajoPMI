package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JPasswordField txtContraseña;
    private JPasswordField txtConfirmarContraseña;
    private JButton btnRegistrar;
    private JButton btnVolver;
    private JRadioButton rdbtnAlumno;
    private JRadioButton rdbtnProfesor;

    /**
     * Create the frame.
     */
    public VistaRegistro() {
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 10));
        
        // Panel principal con datos comunes
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(6, 2, 5, 10));
        
        // DNI
        panelDatos.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        panelDatos.add(txtDni);
        
        // Nombre
        panelDatos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelDatos.add(txtNombre);
        
        // Apellido
        panelDatos.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelDatos.add(txtApellido);
        
        // Contraseña
        panelDatos.add(new JLabel("Contraseña:"));
        txtContraseña = new JPasswordField();
        panelDatos.add(txtContraseña);
        
        // Confirmar contraseña
        panelDatos.add(new JLabel("Confirmar contraseña:"));
        txtConfirmarContraseña = new JPasswordField();
        panelDatos.add(txtConfirmarContraseña);
        
        // Tipo de usuario
        panelDatos.add(new JLabel("Tipo de usuario:"));
        JPanel panelTipoUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rdbtnAlumno = new JRadioButton("Alumno");
        rdbtnAlumno.setSelected(true);
        rdbtnProfesor = new JRadioButton("Profesor");
        
        // Agrupar los radio buttons
        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(rdbtnAlumno);
        grupoTipo.add(rdbtnProfesor);
        
        panelTipoUsuario.add(rdbtnAlumno);
        panelTipoUsuario.add(rdbtnProfesor);
        panelDatos.add(panelTipoUsuario);
        
        contentPane.add(panelDatos, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        btnVolver = new JButton("Volver");
        panelBotones.add(btnVolver);
        
        btnRegistrar = new JButton("Continuar");
        panelBotones.add(btnRegistrar);
        
        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }
    
    // Getters para acceder a los componentes desde el controlador
    public JTextField getTxtDni() {
        return txtDni;
    }
    
    public JTextField getTxtNombre() {
        return txtNombre;
    }
    
    public JTextField getTxtApellido() {
        return txtApellido;
    }
    
    public JPasswordField getTxtContraseña() {
        return txtContraseña;
    }
    
    public JPasswordField getTxtConfirmarContraseña() {
        return txtConfirmarContraseña;
    }
    
    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }
    
    public JButton getBtnVolver() {
        return btnVolver;
    }
    
    public JRadioButton getRdbtnAlumno() {
        return rdbtnAlumno;
    }
    
    public JRadioButton getRdbtnProfesor() {
        return rdbtnProfesor;
    }
}