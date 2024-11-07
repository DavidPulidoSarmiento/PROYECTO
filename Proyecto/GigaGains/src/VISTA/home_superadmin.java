
package VISTA;


import MODELO.Circuito;
import MODELO.CircuitoDAO;
import MODELO.Ejercicio;
import MODELO.EjercicioDAO;
import MODELO.Plan;
import MODELO.PlanDAO;
import MODELO.Rutina;
import MODELO.RutinaDAO;
import MODELO.Usuario;
import MODELO.UsuarioDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class home_superadmin extends javax.swing.JFrame {
    Rutina cl = new Rutina();
    RutinaDAO client = new RutinaDAO();
    Usuario us = new Usuario();
    UsuarioDAO user = new UsuarioDAO();
    Ejercicio ej = new Ejercicio();
    EjercicioDAO ejDao = new EjercicioDAO();
    Plan pla = new Plan();
    PlanDAO plaDao = new PlanDAO();
    Circuito cir = new Circuito();
    CircuitoDAO cirDao = new CircuitoDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    public home_superadmin() {
        initComponents();
        this.setBounds(0,0,1350,750);
        this.setLocationRelativeTo(null);
        llenarComboBoxMusculosOcupados();
        llenarComboBoxEjercicios();
        llenarComboBoxCircuitos();
        llenarComboBoxRutina();
        cargarCircuitos();
        inicializarEventos();
    }
    
    public void ListarCircuito() {
        List<Circuito> ListarCir = cirDao.ListarCircuitos();
        modelo = (DefaultTableModel) TableCircuito.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < ListarCir.size(); i++) {
            ob[0] = ListarCir.get(i).getId();
            ob[1] = ListarCir.get(i).getNombre();
            modelo.addRow(ob);
        }
        
        TableCircuito.setModel(modelo);
    }
    
    public void ListarRutina(){
        List<Rutina> ListarRu = client.ListarRutina();
        modelo = (DefaultTableModel) TableRutina.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < ListarRu.size(); i++){
            ob[0] = ListarRu.get(i).getId();
            ob[1] = ListarRu.get(i).getNombre();
            modelo.addRow(ob);
        }
        TableRutina.setModel(modelo);
    }
    
    public void ListarUsuario(){
        List<Usuario> ListarUs = user.ListarUsuario();
        modelo = (DefaultTableModel) TableUsuario.getModel();
        Object[] ob = new Object[11];
        for (int i = 0; i < ListarUs.size(); i++){
            ob[0] = ListarUs.get(i).getId();
            ob[1] = ListarUs.get(i).getNombre();
            ob[2] = ListarUs.get(i).getEmail();
            ob[3] = ListarUs.get(i).getFecha_de_nacimiento();
            ob[4] = ListarUs.get(i).getFecha_de_registro();
            ob[5] = ListarUs.get(i).getGenero();
            ob[6] = ListarUs.get(i).getContraseña();
            ob[7] = ListarUs.get(i).getEstatura();
            ob[8] = ListarUs.get(i).getPeso();
            ob[9] = ListarUs.get(i).getCondicion_especial();
            ob[10] = ListarUs.get(i).getId_plan();
            modelo.addRow(ob);
        }
        TableUsuario.setModel(modelo);
    }
    
    public void ListarEjercicio() {
    List<Ejercicio> ListarEj = ejDao.ListarEjercicio();
    modelo = (DefaultTableModel) tableEjercicios.getModel();
    Object[] ob = new Object[6];
    for (int i = 0; i < ListarEj.size(); i++) {
        ob[0] = ListarEj.get(i).getId();
        ob[1] = ListarEj.get(i).getNombre();
        ob[2] = ListarEj.get(i).getDescripcion();
        ob[3] = ListarEj.get(i).getVisual();
        ob[4] = ListarEj.get(i).getNombreMusculo();  // Mostrar el nombre del músculo
        modelo.addRow(ob);
    }
    tableEjercicios.setModel(modelo);
    }
    
    public void ListarPlan(){
        List<Plan> ListarPla = plaDao.ListarPlan();
        modelo = (DefaultTableModel) TablePlan.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarPla.size(); i++){
            ob[0] = ListarPla.get(i).getId();
            ob[1] = ListarPla.get(i).getTipo();
            ob[2] = ListarPla.get(i).getId_dieta();
            ob[3] = ListarPla.get(i).getId_rutina();
            modelo.addRow(ob);
        }
        TablePlan.setModel(modelo);
    }
    private void llenarComboBoxMusculosOcupados() {
        List<String> nombres = ejDao.obtenerNombresMusculosOcupados();
        jComboBoxMusculosOcupados.removeAllItems();  // Limpiar el JComboBox

        for (String nombre : nombres) {
            jComboBoxMusculosOcupados.addItem(nombre);  // Agregar cada nombre
        }
    }
    private void llenarComboBoxEjercicios() {
        List<String> nombres = cirDao.obtenerNombresEjercicios();
        combocejercicio.removeAllItems(); 

        for (String nombre : nombres) {
            combocejercicio.addItem(nombre);
        }
    }
    private void llenarComboBoxCircuitos() {
        List<String> nombres = cirDao.obtenerNombresCircuitos();
        comboxcircuito.removeAllItems(); 
        combocircuito.removeAllItems();

        for (String nombre : nombres) {
            comboxcircuito.addItem(nombre);
            combocircuito.addItem(nombre);
        }
    }
    private void cargarCircuitos() {
        CircuitoDAO dao = new CircuitoDAO();
        List<String> nombresCircuitos = dao.obtenerNombresCircuitos();
        for (String nombre : nombresCircuitos) {
            comboxcircuito.addItem(nombre);
        }
    }
    private void llenarComboBoxRutina() {
        List<String> nombres = client.obtenerNombresRutinas();
        comborutinas.removeAllItems(); 

        for (String nombre : nombres) {
            comborutinas.addItem(nombre);
        }
    }
    private void inicializarEventos() {
        comboxcircuito.addActionListener(e -> {
            String nombreCircuitoSeleccionado = (String) comboxcircuito.getSelectedItem();
            if (nombreCircuitoSeleccionado != null) {
                actualizarTablaEjercicios(nombreCircuitoSeleccionado);
            }
        });
        comborutinas.addActionListener(e -> {
            String nombreCircuitoSeleccionado = (String) comborutinas.getSelectedItem();
            if (nombreCircuitoSeleccionado != null) {
                actualizarTablaCircuitos(nombreCircuitoSeleccionado);
            }
        });
    }
    private void actualizarTablaEjercicios(String nombreCircuito) {
        CircuitoDAO dao = new CircuitoDAO();
        List<Circuito> listaEjercicios = dao.obtenerEjerciciosPorCircuito(nombreCircuito);
        // Limpiar la tabla antes de agregar nuevos datos
        DefaultTableModel modelo = (DefaultTableModel) TableCircuitoEjercicio.getModel();
        modelo.setRowCount(0);
        // Agregar filas con datos de los ejercicios
        for (Circuito ejercicio : listaEjercicios) {
            Object[] fila = {
                ejercicio.getIDCircuitoEj(),
                ejercicio.getNombre_ejercicio(),
                ejercicio.getSeries()
            };
            modelo.addRow(fila);
        }
    }
    private void actualizarTablaCircuitos(String nombreRutina) {
        RutinaDAO dao = new RutinaDAO();
        List<Rutina> listaRutinas = dao.obtenerCircuitoporRutina(nombreRutina);
        // Limpiar la tabla antes de agregar nuevos datos
        DefaultTableModel modelo = (DefaultTableModel) TableCircuitoEjercicio.getModel();
        modelo.setRowCount(0);
        // Agregar filas con datos de los ejercicios
        for (Rutina rutina : listaRutinas) {
            Object[] fila = {
                rutina.getIdcircuito(),
                rutina.getNombrecircuito()
            };
            modelo.addRow(fila);
        }
    }
    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        btnCircuito = new javax.swing.JButton();
        btnEjercicios = new javax.swing.JButton();
        btnRutina = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        btnPlan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtSeries = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtIDCircuito = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtNombreCircuito = new javax.swing.JTextField();
        btnCrearCircuito = new javax.swing.JButton();
        btnEditarCircuito = new javax.swing.JButton();
        btnBorrarCircuito = new javax.swing.JButton();
        BtnCancelarCircuito = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableCircuito = new javax.swing.JTable();
        txtIDCircuitoEj = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        xd1 = new javax.swing.JLabel();
        comboxcircuito = new javax.swing.JComboBox<>();
        btnCrearCiEj = new javax.swing.JButton();
        btnBorrarCiEj = new javax.swing.JButton();
        btnCancelarCiEj = new javax.swing.JButton();
        combocejercicio = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TableCircuitoEjercicio = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtIdEjercicio = new javax.swing.JTextField();
        btnCrearEjercicios = new javax.swing.JButton();
        btnCancelarEjercicios = new javax.swing.JButton();
        btnActualizarEjercicios = new javax.swing.JButton();
        txtVisualEjercicio = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtNombreEjercicio = new javax.swing.JTextField();
        txtDescripcionEjercicio = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jComboBoxMusculosOcupados = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableEjercicios = new javax.swing.JTable();
        btnBorrarEjercicios = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtIDRutina = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtNombreRutina = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btnEditarRutina = new javax.swing.JButton();
        Iniciar14 = new javax.swing.JButton();
        btnCrearRutina = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableRutina = new javax.swing.JTable();
        btnBorrarRutina = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableRutina1 = new javax.swing.JTable();
        combocircuito = new javax.swing.JComboBox<>();
        xd = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtIDRutina1 = new javax.swing.JTextField();
        Iniciar15 = new javax.swing.JButton();
        btnBorrarRutina1 = new javax.swing.JButton();
        btnCrearRutinaCI = new javax.swing.JButton();
        comborutinas = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtEmailUsuario = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        btnBorrarUsuario = new javax.swing.JButton();
        btnEditarUsuario = new javax.swing.JButton();
        Iniciar18 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableUsuario = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        txtFechaRegistro = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtGeneroUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEstaturaUsuario = new javax.swing.JTextField();
        txtPesoUsuario = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtCondEspecialUsuario = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtPlanUsuario = new javax.swing.JTextField();
        txtContraseñaUsuario = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtTipoPlan = new javax.swing.JTextField();
        txtIdPlan = new javax.swing.JTextField();
        btnCrearPlan = new javax.swing.JButton();
        btnEditarPlan = new javax.swing.JButton();
        btnBorrarPlan = new javax.swing.JButton();
        btnCancelarPlan = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablePlan = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        txtIDRutinaPlan = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtIdDietaPlan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(144, 12, 63));
        setSize(new java.awt.Dimension(1350, 725));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/logoGiGa.png"))); // NOI18N
        jPanel2.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 15, -1, -1));

        btnCircuito.setBackground(new java.awt.Color(144, 12, 63));
        btnCircuito.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnCircuito.setForeground(new java.awt.Color(255, 255, 255));
        btnCircuito.setText("CIRCUITO");
        btnCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCircuitoActionPerformed(evt);
            }
        });
        jPanel2.add(btnCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, -1));

        btnEjercicios.setBackground(new java.awt.Color(144, 12, 63));
        btnEjercicios.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnEjercicios.setForeground(new java.awt.Color(255, 255, 255));
        btnEjercicios.setText("EJERCICIOS");
        btnEjercicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjerciciosActionPerformed(evt);
            }
        });
        jPanel2.add(btnEjercicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, -1, -1));

        btnRutina.setBackground(new java.awt.Color(144, 12, 63));
        btnRutina.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnRutina.setForeground(new java.awt.Color(255, 255, 255));
        btnRutina.setText("RUTINA");
        btnRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutinaActionPerformed(evt);
            }
        });
        jPanel2.add(btnRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 60, -1, -1));

        btnUsuario.setBackground(new java.awt.Color(144, 12, 63));
        btnUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnUsuario.setText("USUARIO");
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(btnUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, -1, -1));

        btnPlan.setBackground(new java.awt.Color(144, 12, 63));
        btnPlan.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnPlan.setForeground(new java.awt.Color(255, 255, 255));
        btnPlan.setText("PLAN");
        btnPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlanActionPerformed(evt);
            }
        });
        jPanel2.add(btnPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 60, -1, -1));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1350, 130);

        jPanel1.setBackground(new java.awt.Color(144, 12, 63));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(144, 12, 63));

        jPanel3.setBackground(new java.awt.Color(144, 12, 63));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Series");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 20, -1, -1));

        txtSeries.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSeriesKeyTyped(evt);
            }
        });
        jPanel3.add(txtSeries, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 40, 60, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("ID");
        jPanel3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtIDCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDCircuitoActionPerformed(evt);
            }
        });
        txtIDCircuito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDCircuitoKeyTyped(evt);
            }
        });
        jPanel3.add(txtIDCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 40, -1));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Nombre");
        jPanel3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        txtNombreCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCircuitoActionPerformed(evt);
            }
        });
        txtNombreCircuito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreCircuitoKeyTyped(evt);
            }
        });
        jPanel3.add(txtNombreCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 110, -1));

        btnCrearCircuito.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCrearCircuito.setForeground(new java.awt.Color(144, 12, 63));
        btnCrearCircuito.setText("CREAR");
        btnCrearCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCircuitoActionPerformed(evt);
            }
        });
        jPanel3.add(btnCrearCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        btnEditarCircuito.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnEditarCircuito.setForeground(new java.awt.Color(144, 12, 63));
        btnEditarCircuito.setText("ACTUALIZAR");
        btnEditarCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCircuitoActionPerformed(evt);
            }
        });
        jPanel3.add(btnEditarCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        btnBorrarCircuito.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnBorrarCircuito.setForeground(new java.awt.Color(144, 12, 63));
        btnBorrarCircuito.setText("BORRAR");
        btnBorrarCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarCircuitoActionPerformed(evt);
            }
        });
        jPanel3.add(btnBorrarCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        BtnCancelarCircuito.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        BtnCancelarCircuito.setForeground(new java.awt.Color(144, 12, 63));
        BtnCancelarCircuito.setText("CANCELAR");
        BtnCancelarCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarCircuitoActionPerformed(evt);
            }
        });
        jPanel3.add(BtnCancelarCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        TableCircuito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        TableCircuito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCircuitoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(TableCircuito);
        if (TableCircuito.getColumnModel().getColumnCount() > 0) {
            TableCircuito.getColumnModel().getColumn(0).setMinWidth(50);
            TableCircuito.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableCircuito.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel3.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 770, -1));

        txtIDCircuitoEj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDCircuitoEjKeyTyped(evt);
            }
        });
        jPanel3.add(txtIDCircuitoEj, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, 40, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("ID");
        jPanel3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, -1, -1));

        xd1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        xd1.setForeground(new java.awt.Color(255, 255, 255));
        xd1.setText("Nombre");
        jPanel3.add(xd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, -1, -1));

        comboxcircuito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboxcircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxcircuitoActionPerformed(evt);
            }
        });
        jPanel3.add(comboxcircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 120, -1));

        btnCrearCiEj.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCrearCiEj.setForeground(new java.awt.Color(144, 12, 63));
        btnCrearCiEj.setText("CREAR");
        btnCrearCiEj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCiEjActionPerformed(evt);
            }
        });
        jPanel3.add(btnCrearCiEj, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, -1, -1));

        btnBorrarCiEj.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnBorrarCiEj.setForeground(new java.awt.Color(144, 12, 63));
        btnBorrarCiEj.setText("BORRAR");
        btnBorrarCiEj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarCiEjActionPerformed(evt);
            }
        });
        jPanel3.add(btnBorrarCiEj, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, -1, -1));

        btnCancelarCiEj.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCancelarCiEj.setForeground(new java.awt.Color(144, 12, 63));
        btnCancelarCiEj.setText("CANCELAR");
        btnCancelarCiEj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCiEjActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelarCiEj, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, -1, -1));

        combocejercicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combocejercicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocejercicioActionPerformed(evt);
            }
        });
        jPanel3.add(combocejercicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 40, 120, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Nombre Ejercicio");
        jPanel3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 20, -1, -1));

        TableCircuitoEjercicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ejercicios", "Series"
            }
        ));
        TableCircuitoEjercicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCircuitoEjercicioMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(TableCircuitoEjercicio);
        if (TableCircuitoEjercicio.getColumnModel().getColumnCount() > 0) {
            TableCircuitoEjercicio.getColumnModel().getColumn(0).setMinWidth(50);
            TableCircuitoEjercicio.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableCircuitoEjercicio.getColumnModel().getColumn(0).setMaxWidth(50);
            TableCircuitoEjercicio.getColumnModel().getColumn(2).setMinWidth(100);
            TableCircuitoEjercicio.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableCircuitoEjercicio.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        jPanel3.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 480, -1));

        jTabbedPane1.addTab("tab1", jPanel3);

        jPanel4.setBackground(new java.awt.Color(144, 12, 63));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Nombre");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Descripcion");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        txtIdEjercicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdEjercicioKeyTyped(evt);
            }
        });
        jPanel4.add(txtIdEjercicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 40, -1));

        btnCrearEjercicios.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCrearEjercicios.setForeground(new java.awt.Color(144, 12, 63));
        btnCrearEjercicios.setText("CREAR");
        btnCrearEjercicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearEjerciciosActionPerformed(evt);
            }
        });
        jPanel4.add(btnCrearEjercicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        btnCancelarEjercicios.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCancelarEjercicios.setForeground(new java.awt.Color(144, 12, 63));
        btnCancelarEjercicios.setText("CANCELAR");
        btnCancelarEjercicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEjerciciosActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelarEjercicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        btnActualizarEjercicios.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnActualizarEjercicios.setForeground(new java.awt.Color(144, 12, 63));
        btnActualizarEjercicios.setText("ACTUALIZAR");
        btnActualizarEjercicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEjerciciosActionPerformed(evt);
            }
        });
        jPanel4.add(btnActualizarEjercicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));
        jPanel4.add(txtVisualEjercicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 110, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Visual");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        txtNombreEjercicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEjercicioKeyTyped(evt);
            }
        });
        jPanel4.add(txtNombreEjercicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 110, -1));

        txtDescripcionEjercicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionEjercicioKeyTyped(evt);
            }
        });
        jPanel4.add(txtDescripcionEjercicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 110, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Musculos Ocupados");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jComboBoxMusculosOcupados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMusculosOcupados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMusculosOcupadosActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBoxMusculosOcupados, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 140, -1));

        tableEjercicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Visual", "Musculos Ocupados"
            }
        ));
        tableEjercicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEjerciciosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableEjerciciosMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tableEjercicios);
        if (tableEjercicios.getColumnModel().getColumnCount() > 0) {
            tableEjercicios.getColumnModel().getColumn(0).setMinWidth(50);
            tableEjercicios.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableEjercicios.getColumnModel().getColumn(0).setMaxWidth(50);
            tableEjercicios.getColumnModel().getColumn(2).setMinWidth(250);
            tableEjercicios.getColumnModel().getColumn(2).setPreferredWidth(250);
            tableEjercicios.getColumnModel().getColumn(2).setMaxWidth(250);
            tableEjercicios.getColumnModel().getColumn(3).setMinWidth(100);
            tableEjercicios.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableEjercicios.getColumnModel().getColumn(3).setMaxWidth(100);
            tableEjercicios.getColumnModel().getColumn(4).setMinWidth(150);
            tableEjercicios.getColumnModel().getColumn(4).setPreferredWidth(150);
            tableEjercicios.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 1290, -1));

        btnBorrarEjercicios.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnBorrarEjercicios.setForeground(new java.awt.Color(144, 12, 63));
        btnBorrarEjercicios.setText("BORRAR");
        btnBorrarEjercicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarEjerciciosActionPerformed(evt);
            }
        });
        jPanel4.add(btnBorrarEjercicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        jTabbedPane1.addTab("tab2", jPanel4);

        jPanel5.setBackground(new java.awt.Color(144, 12, 63));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtIDRutina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDRutinaKeyTyped(evt);
            }
        });
        jPanel5.add(txtIDRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 40, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Nombre");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        txtNombreRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreRutinaActionPerformed(evt);
            }
        });
        txtNombreRutina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreRutinaKeyTyped(evt);
            }
        });
        jPanel5.add(txtNombreRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 110, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Nombre Circuito");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 20, -1, -1));

        btnEditarRutina.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnEditarRutina.setForeground(new java.awt.Color(144, 12, 63));
        btnEditarRutina.setText("ACTUALIZAR");
        btnEditarRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarRutinaActionPerformed(evt);
            }
        });
        jPanel5.add(btnEditarRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        Iniciar14.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Iniciar14.setForeground(new java.awt.Color(144, 12, 63));
        Iniciar14.setText("CANCELAR");
        Iniciar14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Iniciar14ActionPerformed(evt);
            }
        });
        jPanel5.add(Iniciar14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        btnCrearRutina.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCrearRutina.setForeground(new java.awt.Color(144, 12, 63));
        btnCrearRutina.setText("CREAR");
        btnCrearRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearRutinaActionPerformed(evt);
            }
        });
        jPanel5.add(btnCrearRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        TableRutina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        TableRutina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableRutinaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableRutina);
        if (TableRutina.getColumnModel().getColumnCount() > 0) {
            TableRutina.getColumnModel().getColumn(0).setMinWidth(50);
            TableRutina.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableRutina.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 770, -1));

        btnBorrarRutina.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnBorrarRutina.setForeground(new java.awt.Color(144, 12, 63));
        btnBorrarRutina.setText("BORRAR");
        btnBorrarRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarRutinaActionPerformed(evt);
            }
        });
        jPanel5.add(btnBorrarRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        TableRutina1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Circuitos"
            }
        ));
        TableRutina1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableRutina1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TableRutina1);
        if (TableRutina1.getColumnModel().getColumnCount() > 0) {
            TableRutina1.getColumnModel().getColumn(0).setMinWidth(50);
            TableRutina1.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableRutina1.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 480, -1));

        combocircuito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combocircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocircuitoActionPerformed(evt);
            }
        });
        jPanel5.add(combocircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 40, 120, -1));

        xd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        xd.setForeground(new java.awt.Color(255, 255, 255));
        xd.setText("Nombre");
        jPanel5.add(xd, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, -1, -1));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("ID");
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, -1, -1));

        txtIDRutina1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDRutina1KeyTyped(evt);
            }
        });
        jPanel5.add(txtIDRutina1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, 40, -1));

        Iniciar15.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Iniciar15.setForeground(new java.awt.Color(144, 12, 63));
        Iniciar15.setText("CANCELAR");
        Iniciar15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Iniciar15ActionPerformed(evt);
            }
        });
        jPanel5.add(Iniciar15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, -1, -1));

        btnBorrarRutina1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnBorrarRutina1.setForeground(new java.awt.Color(144, 12, 63));
        btnBorrarRutina1.setText("BORRAR");
        btnBorrarRutina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarRutina1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnBorrarRutina1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, -1, -1));

        btnCrearRutinaCI.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCrearRutinaCI.setForeground(new java.awt.Color(144, 12, 63));
        btnCrearRutinaCI.setText("CREAR");
        btnCrearRutinaCI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearRutinaCIActionPerformed(evt);
            }
        });
        jPanel5.add(btnCrearRutinaCI, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, -1, -1));

        comborutinas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comborutinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comborutinasActionPerformed(evt);
            }
        });
        jPanel5.add(comborutinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 120, -1));

        jTabbedPane1.addTab("tab3", jPanel5);

        jPanel6.setBackground(new java.awt.Color(144, 12, 63));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtIdUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdUsuarioKeyTyped(evt);
            }
        });
        jPanel6.add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 40, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Nombre");
        jPanel6.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        txtNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreUsuarioKeyTyped(evt);
            }
        });
        jPanel6.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 110, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Email");
        jPanel6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        txtEmailUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailUsuarioKeyTyped(evt);
            }
        });
        jPanel6.add(txtEmailUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 140, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Fecha Nacimiento");
        jPanel6.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));
        jPanel6.add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 140, -1));

        btnBorrarUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnBorrarUsuario.setForeground(new java.awt.Color(144, 12, 63));
        btnBorrarUsuario.setText("BORRAR");
        btnBorrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarUsuarioActionPerformed(evt);
            }
        });
        jPanel6.add(btnBorrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        btnEditarUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnEditarUsuario.setForeground(new java.awt.Color(144, 12, 63));
        btnEditarUsuario.setText("ACTUALIZAR");
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });
        jPanel6.add(btnEditarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        Iniciar18.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Iniciar18.setForeground(new java.awt.Color(144, 12, 63));
        Iniciar18.setText("CANCELAR");
        Iniciar18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Iniciar18ActionPerformed(evt);
            }
        });
        jPanel6.add(Iniciar18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        TableUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Email", "Fecha Nacimiento", "Fecha Registro", "Genero", "Contraseña", "Estatura", "Peso", "Cond. Especial", "Plan"
            }
        ));
        TableUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUsuarioMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableUsuario);
        if (TableUsuario.getColumnModel().getColumnCount() > 0) {
            TableUsuario.getColumnModel().getColumn(0).setMinWidth(50);
            TableUsuario.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableUsuario.getColumnModel().getColumn(0).setMaxWidth(50);
            TableUsuario.getColumnModel().getColumn(1).setMinWidth(150);
            TableUsuario.getColumnModel().getColumn(1).setPreferredWidth(150);
            TableUsuario.getColumnModel().getColumn(1).setMaxWidth(150);
            TableUsuario.getColumnModel().getColumn(2).setMinWidth(200);
            TableUsuario.getColumnModel().getColumn(2).setPreferredWidth(200);
            TableUsuario.getColumnModel().getColumn(2).setMaxWidth(200);
            TableUsuario.getColumnModel().getColumn(3).setMinWidth(120);
            TableUsuario.getColumnModel().getColumn(3).setPreferredWidth(120);
            TableUsuario.getColumnModel().getColumn(3).setMaxWidth(120);
            TableUsuario.getColumnModel().getColumn(4).setMinWidth(120);
            TableUsuario.getColumnModel().getColumn(4).setPreferredWidth(120);
            TableUsuario.getColumnModel().getColumn(4).setMaxWidth(120);
            TableUsuario.getColumnModel().getColumn(5).setMinWidth(100);
            TableUsuario.getColumnModel().getColumn(5).setPreferredWidth(100);
            TableUsuario.getColumnModel().getColumn(5).setMaxWidth(100);
            TableUsuario.getColumnModel().getColumn(6).setMinWidth(150);
            TableUsuario.getColumnModel().getColumn(6).setPreferredWidth(150);
            TableUsuario.getColumnModel().getColumn(6).setMaxWidth(150);
            TableUsuario.getColumnModel().getColumn(7).setMinWidth(50);
            TableUsuario.getColumnModel().getColumn(7).setPreferredWidth(50);
            TableUsuario.getColumnModel().getColumn(7).setMaxWidth(50);
            TableUsuario.getColumnModel().getColumn(8).setMinWidth(50);
            TableUsuario.getColumnModel().getColumn(8).setPreferredWidth(50);
            TableUsuario.getColumnModel().getColumn(8).setMaxWidth(50);
            TableUsuario.getColumnModel().getColumn(10).setMinWidth(50);
            TableUsuario.getColumnModel().getColumn(10).setPreferredWidth(50);
            TableUsuario.getColumnModel().getColumn(10).setMaxWidth(50);
        }

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 1290, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Fecha Registro");
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));
        jPanel6.add(txtFechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 140, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Genero");
        jPanel6.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Contraseña");
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, -1, -1));

        txtGeneroUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGeneroUsuarioKeyTyped(evt);
            }
        });
        jPanel6.add(txtGeneroUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, 110, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Estatura");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, -1, -1));

        txtEstaturaUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstaturaUsuarioKeyTyped(evt);
            }
        });
        jPanel6.add(txtEstaturaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 60, -1));

        txtPesoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoUsuarioKeyTyped(evt);
            }
        });
        jPanel6.add(txtPesoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 40, 60, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Peso");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Cond. Especial");
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 20, -1, -1));

        txtCondEspecialUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCondEspecialUsuarioKeyTyped(evt);
            }
        });
        jPanel6.add(txtCondEspecialUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 40, 110, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Plan");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 20, -1, -1));

        txtPlanUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlanUsuarioKeyTyped(evt);
            }
        });
        jPanel6.add(txtPlanUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 40, 40, -1));
        jPanel6.add(txtContraseñaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 110, -1));

        jTabbedPane1.addTab("tab4", jPanel6);

        jPanel7.setBackground(new java.awt.Color(144, 12, 63));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("ID");
        jPanel7.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Tipo");
        jPanel7.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        txtTipoPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoPlanActionPerformed(evt);
            }
        });
        txtTipoPlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoPlanKeyTyped(evt);
            }
        });
        jPanel7.add(txtTipoPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 110, -1));

        txtIdPlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdPlanKeyTyped(evt);
            }
        });
        jPanel7.add(txtIdPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 40, -1));

        btnCrearPlan.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCrearPlan.setForeground(new java.awt.Color(144, 12, 63));
        btnCrearPlan.setText("CREAR");
        btnCrearPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPlanActionPerformed(evt);
            }
        });
        jPanel7.add(btnCrearPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        btnEditarPlan.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnEditarPlan.setForeground(new java.awt.Color(144, 12, 63));
        btnEditarPlan.setText("ACTUALIZAR");
        btnEditarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPlanActionPerformed(evt);
            }
        });
        jPanel7.add(btnEditarPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        btnBorrarPlan.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnBorrarPlan.setForeground(new java.awt.Color(144, 12, 63));
        btnBorrarPlan.setText("BORRAR");
        btnBorrarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarPlanActionPerformed(evt);
            }
        });
        jPanel7.add(btnBorrarPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        btnCancelarPlan.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCancelarPlan.setForeground(new java.awt.Color(144, 12, 63));
        btnCancelarPlan.setText("CANCELAR");
        btnCancelarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPlanActionPerformed(evt);
            }
        });
        jPanel7.add(btnCancelarPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        TablePlan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo", "ID's Dieta", "ID's Rutina"
            }
        ));
        TablePlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePlanMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablePlan);
        if (TablePlan.getColumnModel().getColumnCount() > 0) {
            TablePlan.getColumnModel().getColumn(0).setMinWidth(50);
            TablePlan.getColumnModel().getColumn(0).setPreferredWidth(50);
            TablePlan.getColumnModel().getColumn(0).setMaxWidth(50);
            TablePlan.getColumnModel().getColumn(2).setMinWidth(100);
            TablePlan.getColumnModel().getColumn(2).setPreferredWidth(100);
            TablePlan.getColumnModel().getColumn(2).setMaxWidth(100);
            TablePlan.getColumnModel().getColumn(3).setMinWidth(100);
            TablePlan.getColumnModel().getColumn(3).setPreferredWidth(100);
            TablePlan.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jPanel7.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 1290, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("ID_Dieta");
        jPanel7.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        txtIDRutinaPlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDRutinaPlanKeyTyped(evt);
            }
        });
        jPanel7.add(txtIDRutinaPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 70, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("ID_Rutina");
        jPanel7.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        txtIdDietaPlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdDietaPlanKeyTyped(evt);
            }
        });
        jPanel7.add(txtIdDietaPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 70, -1));

        jTabbedPane1.addTab("tab5", jPanel7);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 1350, 600));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1350, 730);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanActionPerformed
       LimpiarTable();
       ListarPlan();
       btnEditarPlan.setEnabled(false);
       btnBorrarPlan.setEnabled(false);
       btnCrearPlan.setEnabled(true);
       LimpiarPlan();
       jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_btnPlanActionPerformed

    private void btnCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCircuitoActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarCircuito();
        LimpiarCircuitoEj();
        btnEditarCircuito.setEnabled(false);
        btnBorrarCircuito.setEnabled(false);
        btnCrearCircuito.setEnabled(true);
        LimpiarCircuito();
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnCircuitoActionPerformed

    private void btnRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutinaActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarRutina();
        btnEditarRutina.setEnabled(false);
        btnBorrarRutina.setEnabled(false);
        btnCrearRutina.setEnabled(true);
        LimpiarRutina();
        LimpiarRutinaCi();
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnRutinaActionPerformed

    private void btnEjerciciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjerciciosActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarEjercicio();
        btnActualizarEjercicios.setEnabled(false);
        btnBorrarEjercicios.setEnabled(false);
        btnCrearEjercicios.setEnabled(true);
        LimpiarEjercicio();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnEjerciciosActionPerformed

    private void btnCrearEjerciciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearEjerciciosActionPerformed
        // TODO add your handling code here:
        if (!txtIdEjercicio.getText().isEmpty() && 
        !txtNombreEjercicio.getText().isEmpty() && 
        !txtDescripcionEjercicio.getText().isEmpty() && 
        !txtVisualEjercicio.getText().isEmpty()) {
            
            ej.setId(Integer.parseInt(txtIdEjercicio.getText()));
            ej.setNombre(txtNombreEjercicio.getText());
            ej.setDescripcion(txtDescripcionEjercicio.getText());
            ej.setVisual(txtVisualEjercicio.getText());
            ej.setNombreMusculo((String) jComboBoxMusculosOcupados.getSelectedItem());
            ejDao.RegistrarEjercicio(ej);
            JOptionPane.showMessageDialog(null, "Ejercicio registrado");

            // Limpiar los campos después de registrar
            LimpiarEjercicio();
        
    } else {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
    }
    }//GEN-LAST:event_btnCrearEjerciciosActionPerformed

    private void btnCancelarEjerciciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEjerciciosActionPerformed
        // TODO add your handling code here:
        LimpiarEjercicio();
        btnActualizarEjercicios.setEnabled(false);
        btnBorrarEjercicios.setEnabled(false);
        btnCrearEjercicios.setEnabled(true);
    }//GEN-LAST:event_btnCancelarEjerciciosActionPerformed

    private void btnActualizarEjerciciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEjerciciosActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdEjercicio.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtIdEjercicio.getText()) || !"".equals(txtNombreEjercicio.getText()) || !"".equals(txtDescripcionEjercicio.getText()) || !"".equals(txtVisualEjercicio.getText())) {
                ej.setId(Integer.parseInt(txtIdEjercicio.getText()));
                ej.setNombre(txtNombreEjercicio.getText());
                ej.setDescripcion(txtDescripcionEjercicio.getText());
                ej.setVisual(txtVisualEjercicio.getText());
                ej.setNombreMusculo((String)jComboBoxMusculosOcupados.getSelectedItem());
                ejDao.ModificarEjercicio(ej);
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                LimpiarTable();
                LimpiarEjercicio();
                ListarEjercicio();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_btnActualizarEjerciciosActionPerformed

    private void btnEditarRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarRutinaActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIDRutina.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtIDRutina.getText()) || !"".equals(txtNombreRutina.getText())) {
                cl.setId(Integer.parseInt(txtIDRutina.getText()));
                cl.setNombre(txtNombreRutina.getText());
                client.ModificarRutina(cl);
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                LimpiarTable();
                LimpiarRutina();
                ListarRutina();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_btnEditarRutinaActionPerformed

    private void Iniciar14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Iniciar14ActionPerformed
        // TODO add your handling code here:
        LimpiarRutina();
        btnEditarRutina.setEnabled(false);
        btnBorrarRutina.setEnabled(false);
        btnCrearRutina.setEnabled(true);
    }//GEN-LAST:event_Iniciar14ActionPerformed

    private void btnCrearRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearRutinaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIDRutina.getText()) || !"".equals(txtNombreRutina.getText())){
            cl.setId(Integer.parseInt(txtIDRutina.getText()));
            cl.setNombre(txtNombreRutina.getText());
            client.RegistrarRutina(cl);
            JOptionPane.showMessageDialog(null, "Rutina registrada");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnCrearRutinaActionPerformed

    private void btnBorrarEjerciciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarEjerciciosActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdEjercicio.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdEjercicio.getText());
                ejDao.EliminarEjercicio(id);
                LimpiarTable();
                LimpiarEjercicio();
                ListarEjercicio();
            }
        }
    }//GEN-LAST:event_btnBorrarEjerciciosActionPerformed

    private void btnBorrarRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarRutinaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIDRutina.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIDRutina.getText());
                client.EliminarRutina(id);
                LimpiarTable();
                LimpiarRutina();
                ListarRutina();
            }
        }
    }//GEN-LAST:event_btnBorrarRutinaActionPerformed

    private void btnBorrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarUsuarioActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdUsuario.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdUsuario.getText());
                user.EliminarUsuario(id);
                LimpiarTable();
                ListarUsuario();
                LimpiarUsuario();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnBorrarUsuarioActionPerformed

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdUsuario.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtIdUsuario.getText()) || !"".equals(txtNombreUsuario.getText()) || !"".equals(txtEmailUsuario.getText()) || !"".equals(txtFechaNacimiento.getText()) || !"".equals(txtFechaRegistro.getText()) || !"".equals(txtGeneroUsuario.getText()) || !"".equals(txtContraseñaUsuario.getText()) || !"".equals(txtEstaturaUsuario.getText()) ||  !"".equals(txtPesoUsuario.getText()) || !"".equals(txtCondEspecialUsuario.getText()) || !"".equals(txtPlanUsuario.getText())) {
                us.setId(Integer.parseInt(txtIdUsuario.getText()));
                us.setNombre(txtNombreUsuario.getText());
                us.setEmail(txtEmailUsuario.getText());
                us.setFecha_de_nacimiento(txtFechaNacimiento.getText());
                us.setFecha_de_registro(txtFechaRegistro.getText());
                us.setGenero(txtGeneroUsuario.getText());
                us.setContraseña(txtContraseñaUsuario.getText());
                us.setEstatura(Double.parseDouble(txtEstaturaUsuario.getText()));
                us.setPeso(Double.parseDouble(txtPesoUsuario.getText()));
                us.setCondicion_especial(txtCondEspecialUsuario.getText());
                us.setId_plan(Integer.parseInt(txtPlanUsuario.getText()));
                user.ModificarUsuario(us);
                JOptionPane.showMessageDialog(null, "Usuario Modificado");
                LimpiarTable();
                LimpiarUsuario();
                ListarUsuario();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    private void Iniciar18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Iniciar18ActionPerformed
        // TODO add your handling code here:
        LimpiarUsuario();
    }//GEN-LAST:event_Iniciar18ActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarUsuario();
        btnEditarUsuario.setEnabled(false);
        btnBorrarUsuario.setEnabled(false);
        LimpiarUsuario();
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void txtNombreRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreRutinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreRutinaActionPerformed

    private void TableRutinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableRutinaMouseClicked
        // TODO add your handling code here:
        btnEditarRutina.setEnabled(true);
        btnBorrarRutina.setEnabled(true);
        btnCrearRutina.setEnabled(false);
        int fila = TableRutina.rowAtPoint(evt.getPoint());
        txtIDRutina.setText(TableRutina.getValueAt(fila, 0).toString());
        txtNombreRutina.setText(TableRutina.getValueAt(fila, 1).toString());
    }//GEN-LAST:event_TableRutinaMouseClicked

    private void jComboBoxMusculosOcupadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMusculosOcupadosActionPerformed
        // TODO add your handling code here:       
    }//GEN-LAST:event_jComboBoxMusculosOcupadosActionPerformed

    private void txtTipoPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoPlanActionPerformed

    private void btnCrearPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPlanActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdPlan.getText()) || !"".equals(txtTipoPlan.getText()) || !"".equals(txtIdDietaPlan.getText()) || !"".equals(txtIDRutinaPlan.getText())){
            pla.setId(Integer.parseInt(txtIdPlan.getText()));
            pla.setTipo(txtTipoPlan.getText());
            pla.setId_dieta(Integer.parseInt(txtIdDietaPlan.getText()));
            pla.setId_rutina(Integer.parseInt(txtIDRutinaPlan.getText()));
            plaDao.RegistrarPlan(pla);
            JOptionPane.showMessageDialog(null, "Rutina registrada");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnCrearPlanActionPerformed

    private void btnEditarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPlanActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdPlan.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtIdPlan.getText()) || !"".equals(txtTipoPlan.getText()) || !"".equals(txtIdDietaPlan.getText()) || !"".equals(txtIDRutinaPlan.getText())) {
                pla.setId(Integer.parseInt(txtIdPlan.getText()));
                pla.setTipo(txtTipoPlan.getText());
                pla.setId_dieta(Integer.parseInt(txtIdDietaPlan.getText()));
                pla.setId_rutina(Integer.parseInt(txtIDRutinaPlan.getText()));
                plaDao.ModificarPlan(pla);
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                LimpiarTable();
                LimpiarPlan();
                ListarPlan();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_btnEditarPlanActionPerformed

    private void btnBorrarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarPlanActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdPlan.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdPlan.getText());
                plaDao.EliminarPlan(id);
                LimpiarTable();
                LimpiarPlan();
                ListarPlan();
            }
        }
    }//GEN-LAST:event_btnBorrarPlanActionPerformed

    private void btnCancelarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPlanActionPerformed
        // TODO add your handling code here:
        LimpiarPlan();
        btnEditarPlan.setEnabled(false);
        btnBorrarPlan.setEnabled(false);
        btnCrearPlan.setEnabled(true);
    }//GEN-LAST:event_btnCancelarPlanActionPerformed

    private void TablePlanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePlanMouseClicked
        // TODO add your handling code here:
        btnEditarPlan.setEnabled(true);
        btnBorrarPlan.setEnabled(true);
        btnCrearPlan.setEnabled(false);
        int fila = TablePlan.rowAtPoint(evt.getPoint());
        txtIdPlan.setText(TablePlan.getValueAt(fila, 0).toString());
        txtTipoPlan.setText(TablePlan.getValueAt(fila, 1).toString());
        txtIdDietaPlan.setText(TablePlan.getValueAt(fila, 2).toString());
        txtIDRutinaPlan.setText(TablePlan.getValueAt(fila, 3).toString());
    }//GEN-LAST:event_TablePlanMouseClicked

    private void TableUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUsuarioMouseClicked
        // TODO add your handling code here:
        btnEditarUsuario.setEnabled(true);
        btnBorrarUsuario.setEnabled(true);
        int fila = TableUsuario.rowAtPoint(evt.getPoint());
        txtIdUsuario.setText(TableUsuario.getValueAt(fila, 0).toString());
        txtNombreUsuario.setText(TableUsuario.getValueAt(fila, 1).toString());
        txtEmailUsuario.setText(TableUsuario.getValueAt(fila, 2).toString());
        txtFechaNacimiento.setText(TableUsuario.getValueAt(fila, 3).toString());
        txtFechaRegistro.setText(TableUsuario.getValueAt(fila, 4).toString());
        txtGeneroUsuario.setText(TableUsuario.getValueAt(fila, 5).toString());
        txtContraseñaUsuario.setText(TableUsuario.getValueAt(fila, 6).toString());
        txtEstaturaUsuario.setText(TableUsuario.getValueAt(fila, 7).toString());
        txtPesoUsuario.setText(TableUsuario.getValueAt(fila, 8).toString());
        txtCondEspecialUsuario.setText(TableUsuario.getValueAt(fila, 9).toString());
        txtPlanUsuario.setText(TableUsuario.getValueAt(fila, 10).toString());
    }//GEN-LAST:event_TableUsuarioMouseClicked

    private void tableEjerciciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEjerciciosMouseClicked
        // TODO add your handling code here:
        btnActualizarEjercicios.setEnabled(true);
        btnBorrarEjercicios.setEnabled(true);
        btnCrearEjercicios.setEnabled(false);
        int fila = tableEjercicios.rowAtPoint(evt.getPoint());
        txtIdEjercicio.setText(tableEjercicios.getValueAt(fila, 0).toString());
        txtNombreEjercicio.setText(tableEjercicios.getValueAt(fila, 1).toString());
        txtDescripcionEjercicio.setText(tableEjercicios.getValueAt(fila, 2).toString());
        txtVisualEjercicio.setText(tableEjercicios.getValueAt(fila, 3).toString());
        jComboBoxMusculosOcupados.setSelectedItem(tableEjercicios.getValueAt(fila, 4));
    }//GEN-LAST:event_tableEjerciciosMouseClicked

    private void tableEjerciciosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEjerciciosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableEjerciciosMouseEntered

    private void TableRutina1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableRutina1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableRutina1MouseClicked

    private void combocircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocircuitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combocircuitoActionPerformed

    private void Iniciar15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Iniciar15ActionPerformed
        // TODO add your handling code here:
        LimpiarRutinaCi();
        btnBorrarRutina1.setEnabled(false);
        btnCrearRutinaCI.setEnabled(true);
    }//GEN-LAST:event_Iniciar15ActionPerformed

    private void btnBorrarRutina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarRutina1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarRutina1ActionPerformed

    private void btnCrearRutinaCIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearRutinaCIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearRutinaCIActionPerformed

    private void comborutinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comborutinasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comborutinasActionPerformed

    private void txtNombreCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCircuitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCircuitoActionPerformed

    private void btnCrearCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCircuitoActionPerformed
        // TODO add your handling code here:
        if (!txtIDCircuito.getText().isEmpty() && 
        !txtNombreCircuito.getText().isEmpty()){
            
            cir.setId(Integer.parseInt(txtIDCircuito.getText()));
            cir.setNombre(txtNombreCircuito.getText());
            cirDao.RegistrarCircuito(cir);
            JOptionPane.showMessageDialog(null, "Ejercicio registrado");

            // Limpiar los campos después de registrar
            LimpiarCircuito();
        
    } else {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
    }
    }//GEN-LAST:event_btnCrearCircuitoActionPerformed

    private void btnEditarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCircuitoActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIDCircuito.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtIDCircuito.getText()) || !"".equals(txtNombreCircuito.getText())) {
                cir.setId(Integer.parseInt(txtIDCircuito.getText()));
                cir.setNombre(txtNombreCircuito.getText());
                cirDao.ModificarCircuito(cir);
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                LimpiarTable();
                LimpiarCircuito();
                ListarCircuito();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_btnEditarCircuitoActionPerformed

    private void btnBorrarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarCircuitoActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIDCircuito.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIDCircuito.getText());
                cirDao.EliminarCircuito(id);
                LimpiarTable();
                LimpiarCircuito();
                ListarCircuito();
            }
        }
    }//GEN-LAST:event_btnBorrarCircuitoActionPerformed

    private void BtnCancelarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarCircuitoActionPerformed
        // TODO add your handling code here:
        LimpiarCircuito();
        btnEditarCircuito.setEnabled(false);
        btnBorrarCircuito.setEnabled(false);
        btnCrearCircuito.setEnabled(true);
    }//GEN-LAST:event_BtnCancelarCircuitoActionPerformed

    private void TableCircuitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCircuitoMouseClicked
        // TODO add your handling code here:btnActualizarEjercicios.setEnabled(true);
        btnBorrarCircuito.setEnabled(true);
        btnEditarCircuito.setEnabled(true);
        btnCrearCircuito.setEnabled(false);
        int fila = TableCircuito.rowAtPoint(evt.getPoint());
        txtIDCircuito.setText(TableCircuito.getValueAt(fila, 0).toString());
        txtNombreCircuito.setText(TableCircuito.getValueAt(fila, 1).toString());
    }//GEN-LAST:event_TableCircuitoMouseClicked

    private void comboxcircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxcircuitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxcircuitoActionPerformed

    private void btnCrearCiEjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCiEjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearCiEjActionPerformed

    private void btnBorrarCiEjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarCiEjActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIDCircuitoEj.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIDCircuitoEj.getText());
                cirDao.EliminarCircuitoEj(id);
                LimpiarTable();
                LimpiarCircuitoEj();
            }
        }
    }//GEN-LAST:event_btnBorrarCiEjActionPerformed

    private void btnCancelarCiEjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCiEjActionPerformed
        // TODO add your handling code here:
        LimpiarCircuitoEj();
        btnBorrarCiEj.setEnabled(false);
        btnCrearCiEj.setEnabled(true);
    }//GEN-LAST:event_btnCancelarCiEjActionPerformed

    private void combocejercicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocejercicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combocejercicioActionPerformed

    private void TableCircuitoEjercicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCircuitoEjercicioMouseClicked
        // TODO add your handling code here:
        btnBorrarCiEj.setEnabled(true);
        btnCrearCiEj.setEnabled(false);
        int fila = TableCircuitoEjercicio.rowAtPoint(evt.getPoint());
        txtIDCircuitoEj.setText(TableCircuitoEjercicio.getValueAt(fila, 0).toString());
        combocejercicio.setSelectedItem(TableCircuitoEjercicio.getValueAt(fila, 1));
        txtSeries.setText(TableCircuitoEjercicio.getValueAt(fila, 2).toString());
    }//GEN-LAST:event_TableCircuitoEjercicioMouseClicked

    private void txtIDCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDCircuitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDCircuitoActionPerformed

    private void txtIDCircuitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDCircuitoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtIDCircuitoKeyTyped

    private void txtNombreCircuitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCircuitoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es una letra o un espacio
    if (!Character.isLetter(c) && c != 'ñ' && c != 'Ñ') {
        evt.consume();  // Descartar el carácter que no sea letra o 'ñ'/'Ñ'
    }
    }//GEN-LAST:event_txtNombreCircuitoKeyTyped

    private void txtIDCircuitoEjKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDCircuitoEjKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtIDCircuitoEjKeyTyped

    private void txtSeriesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeriesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeriesKeyTyped

    private void txtIdEjercicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdEjercicioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtIdEjercicioKeyTyped

    private void txtNombreEjercicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEjercicioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es una letra o un espacio
    if (!Character.isLetter(c) && c != 'ñ' && c != 'Ñ') {
        evt.consume();  // Descartar el carácter que no sea letra o 'ñ'/'Ñ'
    }
    }//GEN-LAST:event_txtNombreEjercicioKeyTyped

    private void txtDescripcionEjercicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionEjercicioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es una letra o un espacio
    if (!Character.isLetter(c) && c != 'ñ' && c != 'Ñ') {
        evt.consume();  // Descartar el carácter que no sea letra o 'ñ'/'Ñ'
    }
    }//GEN-LAST:event_txtDescripcionEjercicioKeyTyped

    private void txtIDRutinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDRutinaKeyTyped
        // TODO add your handling code here:.
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtIDRutinaKeyTyped

    private void txtNombreRutinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreRutinaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es una letra o un espacio
    if (!Character.isLetter(c) && c != 'ñ' && c != 'Ñ') {
        evt.consume();  // Descartar el carácter que no sea letra o 'ñ'/'Ñ'
    }
    }//GEN-LAST:event_txtNombreRutinaKeyTyped

    private void txtIDRutina1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDRutina1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtIDRutina1KeyTyped

    private void txtIdUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdUsuarioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtIdUsuarioKeyTyped

    private void txtNombreUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUsuarioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es una letra o un espacio
    if (!Character.isLetter(c) && c != 'ñ' && c != 'Ñ') {
        evt.consume();  // Descartar el carácter que no sea letra o 'ñ'/'Ñ'
    }
    }//GEN-LAST:event_txtNombreUsuarioKeyTyped

    private void txtEmailUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailUsuarioKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtEmailUsuarioKeyTyped

    private void txtGeneroUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGeneroUsuarioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es una letra o un espacio
    if (!Character.isLetter(c) && c != 'ñ' && c != 'Ñ') {
        evt.consume();  // Descartar el carácter que no sea letra o 'ñ'/'Ñ'
    }
    }//GEN-LAST:event_txtGeneroUsuarioKeyTyped

    private void txtEstaturaUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstaturaUsuarioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtEstaturaUsuarioKeyTyped

    private void txtPesoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoUsuarioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtPesoUsuarioKeyTyped

    private void txtCondEspecialUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCondEspecialUsuarioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es una letra o un espacio
    if (!Character.isLetter(c) && c != 'ñ' && c != 'Ñ') {
        evt.consume();  // Descartar el carácter que no sea letra o 'ñ'/'Ñ'
    }
    }//GEN-LAST:event_txtCondEspecialUsuarioKeyTyped

    private void txtPlanUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlanUsuarioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtPlanUsuarioKeyTyped

    private void txtIdPlanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdPlanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtIdPlanKeyTyped

    private void txtTipoPlanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoPlanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es una letra o un espacio
    if (!Character.isLetter(c) && c != 'ñ' && c != 'Ñ') {
        evt.consume();  // Descartar el carácter que no sea letra o 'ñ'/'Ñ'
    }
    }//GEN-LAST:event_txtTipoPlanKeyTyped

    private void txtIdDietaPlanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdDietaPlanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtIdDietaPlanKeyTyped

    private void txtIDRutinaPlanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDRutinaPlanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    // Verificar si el carácter ingresado no es un número o es un carácter especial
    if (!Character.isDigit(c)) {
        evt.consume();  // Descartar el carácter que no sea número
    }
    }//GEN-LAST:event_txtIDRutinaPlanKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home_superadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new home_superadmin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelarCircuito;
    private javax.swing.JButton Iniciar14;
    private javax.swing.JButton Iniciar15;
    private javax.swing.JButton Iniciar18;
    private javax.swing.JTable TableCircuito;
    private javax.swing.JTable TableCircuitoEjercicio;
    private javax.swing.JTable TablePlan;
    private javax.swing.JTable TableRutina;
    private javax.swing.JTable TableRutina1;
    private javax.swing.JTable TableUsuario;
    private javax.swing.JButton btnActualizarEjercicios;
    private javax.swing.JButton btnBorrarCiEj;
    private javax.swing.JButton btnBorrarCircuito;
    private javax.swing.JButton btnBorrarEjercicios;
    private javax.swing.JButton btnBorrarPlan;
    private javax.swing.JButton btnBorrarRutina;
    private javax.swing.JButton btnBorrarRutina1;
    private javax.swing.JButton btnBorrarUsuario;
    private javax.swing.JButton btnCancelarCiEj;
    private javax.swing.JButton btnCancelarEjercicios;
    private javax.swing.JButton btnCancelarPlan;
    private javax.swing.JButton btnCircuito;
    private javax.swing.JButton btnCrearCiEj;
    private javax.swing.JButton btnCrearCircuito;
    private javax.swing.JButton btnCrearEjercicios;
    private javax.swing.JButton btnCrearPlan;
    private javax.swing.JButton btnCrearRutina;
    private javax.swing.JButton btnCrearRutinaCI;
    private javax.swing.JButton btnEditarCircuito;
    private javax.swing.JButton btnEditarPlan;
    private javax.swing.JButton btnEditarRutina;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnEjercicios;
    private javax.swing.JButton btnPlan;
    private javax.swing.JButton btnRutina;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JComboBox<String> combocejercicio;
    private javax.swing.JComboBox<String> combocircuito;
    private javax.swing.JComboBox<String> comborutinas;
    private javax.swing.JComboBox<String> comboxcircuito;
    private javax.swing.JComboBox<String> jComboBoxMusculosOcupados;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JTable tableEjercicios;
    private javax.swing.JTextField txtCondEspecialUsuario;
    private javax.swing.JTextField txtContraseñaUsuario;
    private javax.swing.JTextField txtDescripcionEjercicio;
    private javax.swing.JTextField txtEmailUsuario;
    private javax.swing.JTextField txtEstaturaUsuario;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFechaRegistro;
    private javax.swing.JTextField txtGeneroUsuario;
    private javax.swing.JTextField txtIDCircuito;
    private javax.swing.JTextField txtIDCircuitoEj;
    private javax.swing.JTextField txtIDRutina;
    private javax.swing.JTextField txtIDRutina1;
    private javax.swing.JTextField txtIDRutinaPlan;
    private javax.swing.JTextField txtIdDietaPlan;
    private javax.swing.JTextField txtIdEjercicio;
    private javax.swing.JTextField txtIdPlan;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNombreCircuito;
    private javax.swing.JTextField txtNombreEjercicio;
    private javax.swing.JTextField txtNombreRutina;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtPesoUsuario;
    private javax.swing.JTextField txtPlanUsuario;
    private javax.swing.JTextField txtSeries;
    private javax.swing.JTextField txtTipoPlan;
    private javax.swing.JTextField txtVisualEjercicio;
    private javax.swing.JLabel xd;
    private javax.swing.JLabel xd1;
    // End of variables declaration//GEN-END:variables
    private void LimpiarCircuito() {
        txtIDCircuito.setText("");
        txtNombreCircuito.setText("");
    }
    private void LimpiarCircuitoEj() {
        comboxcircuito.setSelectedItem(null);
        txtIDCircuitoEj.setText("");
        combocejercicio.setSelectedItem(null);
        txtSeries.setText("");
    }
    private void LimpiarRutina() {
        txtIDRutina.setText("");
        txtNombreRutina.setText("");
    }
    private void LimpiarRutinaCi() {
        comborutinas.setSelectedItem(null);
        txtIDRutina1.setText("");
        combocircuito.setSelectedItem(null);
    }
    private void LimpiarUsuario() {
        txtIdUsuario.setText("");
        txtNombreUsuario.setText("");
        txtEmailUsuario.setText("");
        txtFechaNacimiento.setText("");
        txtFechaRegistro.setText("");
        txtGeneroUsuario.setText("");
        txtContraseñaUsuario.setText("");
        txtEstaturaUsuario.setText("");
        txtPesoUsuario.setText("");
        txtCondEspecialUsuario.setText("");
        txtPlanUsuario.setText("");
    }
    private void LimpiarEjercicio() {
        txtIdEjercicio.setText("");
        txtNombreEjercicio.setText("");
        txtDescripcionEjercicio.setText("");
        txtVisualEjercicio.setText("");
        jComboBoxMusculosOcupados.setSelectedItem(null);
    }
    private void LimpiarPlan() {
        txtIdPlan.setText("");
        txtTipoPlan.setText("");
        txtIdDietaPlan.setText("");
        txtIDRutinaPlan.setText("");
    }
}
