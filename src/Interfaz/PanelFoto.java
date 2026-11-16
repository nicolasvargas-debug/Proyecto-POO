package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelFoto extends JPanel {
    private JLabel labFoto;

    public PanelFoto() {
        setLayout(new BorderLayout());

        labFoto = new JLabel("", SwingConstants.CENTER);
        add(labFoto, BorderLayout.CENTER);
    }

    public void mostrar(File archivo) {
        if (archivo != null && archivo.exists()) {
            ImageIcon icono = new ImageIcon(archivo.getPath());
            Image escalada = icono.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            labFoto.setText("");
            labFoto.setIcon(new ImageIcon(escalada));
        } else {
            labFoto.setIcon(null);
            labFoto.setText("Sin foto");
        }
    }
}