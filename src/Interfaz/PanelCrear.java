package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCrear extends JPanel {
    private JLabel titulo;
    private JLabel[] etiquetas = new JLabel[5];
    private JTextField[] campos = new JTextField[5];

    public PanelCrear(InterfazAdministrador ventana) {
        setLayout(new BorderLayout());
        titulo = new JLabel("Crear");
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(5, 2, 4, 8));
        for (int i = 0; i < 5; i++) {
            etiquetas[i] = new JLabel();
            campos[i] = new JTextField();
            formulario.add(etiquetas[i]);
            formulario.add(campos[i]);
        }

        JButton butCrear = new JButton("Crear");
        butCrear.addActionListener(e -> ventana.crear());

        JPanel arriba = new JPanel(new BorderLayout(0, 10));
        arriba.add(formulario, BorderLayout.NORTH);
        arriba.add(butCrear, BorderLayout.SOUTH);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(arriba, BorderLayout.NORTH);
        add(contenedor, BorderLayout.CENTER);
    }

    public void configurarLibros() {
        configurar("Crear libro", new String[] { "ISBN", "Nombre", "Autor", "Copias totales" });
    }

    public void configurarUsuarios() {
        configurar("Crear usuario", new String[] { "Usuario", "Nombre", "Apellido", "Contraseña" });
    }

    public void configurarAdministradores() {
        configurar("Crear administrador", new String[] { "Usuario", "Nombre", "Apellido", "Contraseña", "Cargo" });
    }

    private void configurar(String textoTitulo, String[] nombres) {
        titulo.setText(textoTitulo);
        for (int i = 0; i < 5; i++) {
            boolean visible = i < nombres.length;
            etiquetas[i].setVisible(visible);
            campos[i].setVisible(visible);
            if (visible) {
                etiquetas[i].setText(nombres[i]);
            }
        }
        limpiar();
    }

    public void limpiar() {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    public String getCampo(int posicion) {
        return campos[posicion].getText().strip();
    }
}