import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Personas {
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton BusquedaCodigo;
    private JButton BusquedaNombre;
    private JComboBox comboBox1;
    private JButton BusquedaSigno;
    private JButton BorrarRegistro;
    private JButton BotonActualizar;
    private JButton BotonIngresar;
    private JButton Limpiar;
    static final String DB_URL = "jdbc:mysql://localhost/prueba";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    public Personas() {
        BusquedaCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        BusquedaNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        BusquedaSigno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        BorrarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoRegistroABorrar = textField1.getText();
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                ) {
                    String sql = "DELETE FROM PERSONAS WHERE Codigo = " + codigoRegistroABorrar;
                    Statement statement = null;
                    try {
                        statement = conn.createStatement();
                        int filasAfectadas = statement.executeUpdate(sql);

                        if (filasAfectadas > 0) {
                            System.out.println("Registro eliminado con éxito.");
                        } else {
                            System.out.println("No se encontró el registro con el ID especificado.");
                        }
                    } finally {
                        if (statement != null) {
                            statement.close();
                        }
                    }
                }catch (SQLException e2){
                    throw new RuntimeException(e2);
                }
            }
        });
        BotonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CODIGON = textField1.getText();
                String CEDULAN = textField2.getText();
                String NOMBREN = textField3.getText();
                String NACIMIENTON = textField4.getText();
                String SIGNON = comboBox1.getSelectedItem().toString();
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                ){
                    String sql = "UPDATE PERSONAS SET Cedula = ?, Nombre = ?, Nacimiento = ?, Signo = ? WHERE Codigo = ?";

                    PreparedStatement preparedStatement = conn.prepareStatement(sql);

                    preparedStatement.setString(1, CEDULAN);
                    preparedStatement.setString(2, NOMBREN);
                    preparedStatement.setString(3, NACIMIENTON);
                    preparedStatement.setString(4, SIGNON);
                    preparedStatement.setString(5, CODIGON);

                    int filasAfectadas = preparedStatement.executeUpdate();

                    if (filasAfectadas > 0) {
                        System.out.println("Registro actualizado con éxito.");
                    } else {
                        System.out.println("No se encontró el registro con el ID especificado.");
                    }

                    // Cerrar la conexión y liberar recursos
                    preparedStatement.close();
                    conn.close();
                } catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        });
        BotonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CODIGOF = textField1.getText();
                String CEDULAF = textField2.getText();
                String NOMBREF = textField3.getText();
                String NACIMIENTOF = textField4.getText();
                String SIGNOF = comboBox1.getSelectedItem().toString();
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                ) {
                    String sql = "INSERT INTO PERSONAS (Codigo, Cedula, Nombre, Nacimiento, Signo) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    try {
                        preparedStatement.setString(1, CODIGOF);
                        preparedStatement.setString(2, CEDULAF);
                        preparedStatement.setString(3, NOMBREF);
                        preparedStatement.setString(4, NACIMIENTOF);
                        preparedStatement.setString(5, SIGNOF);
                        int filasAfectadas = preparedStatement.executeUpdate();

                        if (filasAfectadas > 0) {
                            System.out.println("Registro insertado con éxito.");
                        } else {
                            System.out.println("Error al insertar el registro.");
                        }
                    } finally {
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Personas");
        frame.setContentPane(new Personas().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


