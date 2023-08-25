import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Personas extends Component {
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
    //jdbc:sqlserver://LAPTOP-UC5S954V\MSSQLSERVER01:55580;database=PERSONAS
    static final String DB_URL = "jdbc:sqlserver://LAPTOP-UC5S954V\\MSSQLSERVER01:55580;database=PERSONAS;encrypt=true;trustServerCertificate=true;";
    static final String USER = "userJ";
    static final String PASS = "123";
    public Personas() {
        BusquedaCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = textField1.getText();
                buscarProductoPorCodigo(codigo);
            }
        });
        BusquedaNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField3.getText();
                buscarProductoPorNombre(nombre);
            }
        });
        BusquedaSigno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String signo = comboBox1.getSelectedItem().toString();
                buscarProductoPorSigno(signo);
            }
        });
        BorrarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoRegistroABorrar = textField1.getText();
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                ) {
                    String sql = "DELETE FROM PERSONA WHERE Codigo = " + codigoRegistroABorrar;
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
                    String sql = "UPDATE PERSONA SET Cedula = ?, Nombre = ?, Nacimiento = ?, Signo = ? WHERE Codigo = ?";

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
                    System.out.println("Conexión establecida");
                    String sql = "INSERT INTO PERSONA (Codigo, Cedula, Nombre, Nacimiento, Signo) VALUES (?, ?, ?, ?, ?)";
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

    private void buscarProductoPorCodigo(String codigo) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT Cedula, Nombre, Nacimiento, Signo FROM PERSONA WHERE Codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String Cedula = resultSet.getString("Cedula");
                String Nombre = resultSet.getString("Nombre");
                String Nacimiento = resultSet.getString("Nacimiento");
                String Signo = resultSet.getString("Signo");

                textField2.setText(Nombre);
                textField3.setText(Cedula);
                textField4.setText(Nacimiento);
                comboBox1.addItem(Signo);
            } else {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                JOptionPane.showMessageDialog(this, "Registro no encontrado.");
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar el registro: " + ex.getMessage());
        }
    }

    private void buscarProductoPorNombre(String nombre) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT Codigo, Cedula, Nacimiento, Signo FROM PERSONA WHERE Nombre = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombre);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String Codigo = resultSet.getString("Codigo");
                String Cedula = resultSet.getString("Cedula");
                String Nacimiento = resultSet.getString("Nacimiento");
                String Signo = resultSet.getString("Signo");

                textField1.setText(Codigo);
                textField2.setText(Cedula);
                textField4.setText(Nacimiento);
                comboBox1.addItem(Signo);
            } else {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                JOptionPane.showMessageDialog(this, "Registro no encontrado.");
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar el registro: " + ex.getMessage());
        }
    }

    private void buscarProductoPorSigno(String signo) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT Codigo, Cedula, Nombre, Nacimiento FROM PERSONA WHERE Signo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, signo);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String Codigo = resultSet.getString("Codigo");
                String Cedula = resultSet.getString("Cedula");
                String Nombre = resultSet.getString("Nombre");
                String Nacimiento = resultSet.getString("Nacimiento");

                textField1.setText(Codigo);
                textField2.setText(Cedula);
                textField3.setText(Nombre);
                textField4.setText(Nacimiento);
                System.out.println("Los siguientes registros son:");
                while (resultSet.next()){
                    System.out.println("Codigo: "+resultSet.getString("Codigo"));
                    System.out.println("Cedula: "+resultSet.getString("Cedula"));
                    System.out.println("Nombre: "+resultSet.getString("Nombre"));
                    System.out.println("Nacimiento: "+resultSet.getString("Nacimiento"));
                }
            } else {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                JOptionPane.showMessageDialog(this, "Registro no encontrado.");
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar el registro: " + ex.getMessage());
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Personas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setContentPane(new Personas().rootPanel);
        frame.setVisible(true);
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
    }
}


