import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Personas extends Component {
    private JPanel rootPanel;
    private JTextField txt_codigo;
    private JTextField txt_cedula;
    private JTextField txt_nombre;
    private JTextField txt_fechaN;
    private JButton boton_buscarCodigo;
    private JButton btn_buscarNombre;
    private JComboBox signoZ;
    private JButton btn_buscarsigno;
    private JButton btn_borrar;
    private JButton btn_actualizar;
    private JButton btn_ingresar;
    private JButton btn_limpiar;
    static final String DB_URL = "jdbc:sqlserver://LAPTOP-UC5S954V\\MSSQLSERVER01:55580;database=PERSONAS;encrypt=true;trustServerCertificate=true;";
    static final String USER = "userJ";
    static final String PASS = "123";
    public Personas() {
        boton_buscarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txt_codigo.getText();
                buscarProductoPorCodigo(codigo);
            }
        });
        btn_buscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txt_nombre.getText();
                buscarProductoPorNombre(nombre);
            }
        });
        btn_buscarsigno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String signo = signoZ.getSelectedItem().toString();
                buscarProductoPorSigno(signo);
            }
        });
        btn_borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoRegistroABorrar = txt_codigo.getText();
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
        btn_actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CODIGON = txt_codigo.getText();
                String CEDULAN = txt_cedula.getText();
                String NOMBREN = txt_nombre.getText();
                String NACIMIENTON = txt_fechaN.getText();
                String SIGNON = signoZ.getSelectedItem().toString();
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
        btn_ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CODIGOF = txt_codigo.getText();
                String CEDULAF = txt_cedula.getText();
                String NOMBREF = txt_nombre.getText();
                String NACIMIENTOF = txt_fechaN.getText();
                String SIGNOF = signoZ.getSelectedItem().toString();
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
        btn_limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_codigo.setText("");
                txt_cedula.setText("");
                txt_nombre.setText("");
                txt_fechaN.setText("");
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

                txt_cedula.setText(Nombre);
                txt_nombre.setText(Cedula);
                txt_fechaN.setText(Nacimiento);
                signoZ.addItem(Signo);
            } else {
                txt_codigo.setText("");
                txt_cedula.setText("");
                txt_nombre.setText("");
                txt_fechaN.setText("");
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

                txt_codigo.setText(Codigo);
                txt_cedula.setText(Cedula);
                txt_fechaN.setText(Nacimiento);
                signoZ.addItem(Signo);
            } else {
                txt_codigo.setText("");
                txt_cedula.setText("");
                txt_nombre.setText("");
                txt_fechaN.setText("");
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

                txt_codigo.setText(Codigo);
                txt_cedula.setText(Cedula);
                txt_nombre.setText(Nombre);
                txt_fechaN.setText(Nacimiento);
                System.out.println("Los siguientes registros son:");
                while (resultSet.next()){
                    System.out.println("Codigo: "+resultSet.getString("Codigo"));
                    System.out.println("Cedula: "+resultSet.getString("Cedula"));
                    System.out.println("Nombre: "+resultSet.getString("Nombre"));
                    System.out.println("Nacimiento: "+resultSet.getString("Nacimiento"));
                }
            } else {
                txt_codigo.setText("");
                txt_cedula.setText("");
                txt_nombre.setText("");
                txt_fechaN.setText("");
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


