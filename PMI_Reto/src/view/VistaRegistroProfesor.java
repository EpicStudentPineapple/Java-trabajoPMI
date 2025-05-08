package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaRegistroProfesor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboNivelExperiencia;
    private JTextField txtEspecializacionIdioma;
    private JButton btnRegistrar;
    private JButton btnVolver;
    private String dni;
    private String nombre;
    private String apellido;
    private String contraseña;

    /**
     * Create the frame.
     */
    public VistaRegistroProfesor(String dni, String nombre, String apellido, String contraseña) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        
        setTitle("Registro de Profesor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 250);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 10));
        
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblInfo = new JLabel("Complete la información del profesor:");
        panelInfo.add(lblInfo);
        contentPane.add(panelInfo, BorderLayout.NORTH);
        
        // Panel de datos específicos de profesor
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(2, 2, 5, 10));
        
        // Nivel de experiencia
        panelDatos.add(new JLabel("Nivel de experiencia:"));
        comboNivelExperiencia = new JComboBox<String>();
        comboNivelExperiencia.addItem("Muy alto");
        comboNivelExperiencia.addItem("Alto");
        comboNivelExperiencia.addItem("Medio");
        comboNivelExperiencia.addItem("Bajo");
        comboNivelExperiencia.addItem("Muy bajo");
        panelDatos.add(comboNivelExperiencia);
        
        // Especialización idioma
        panelDatos.add(new JLabel("Especialización idioma:"));
        txtEspecializacionIdioma = new JTextField();
        panelDatos.add(txtEspecializacionIdioma);
        
        contentPane.add(panelDatos, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        btnVolver = new JButton("Volver");
        panelBotones.add(btnVolver);
        
        btnRegistrar = new JButton("Registrar");
        panelBotones.add(btnRegistrar);
        
        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }
    
    // Getters para acceder a los componentes y datos desde el controlador
    public JComboBox<String> getComboNivelExperiencia() {
        return comboNivelExperiencia;
    }
    
    public JTextField getTxtEspecializacionIdioma() {
        return txtEspecializacionIdioma;
    }
    
    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }
    
    public JButton getBtnVolver() {
        return btnVolver;
    }
    
    public String getDni() {
        return dni;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public String getContraseña() {
        return contraseña;
    }
}