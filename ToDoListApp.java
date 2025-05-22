import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoListApp {
    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton completeButton;

    public ToDoListApp() {
        frame = new JFrame("TODO LIST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.GRAY);

        JLabel heading = new JLabel("TODO LIST", JLabel.CENTER);
        heading.setFont(new Font("Verdana", Font.BOLD, 48)); 
        heading.setForeground(Color.BLACK);
        frame.add(heading, BorderLayout.NORTH);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("Verdana", Font.BOLD, 24)); 
        taskList.setBackground(Color.WHITE);
        taskList.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(taskList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.GRAY);

        taskField = new JTextField(25);
        taskField.setFont(new Font("Verdana", Font.BOLD, 22)); 
        taskField.setBackground(Color.WHITE);
        taskField.setForeground(Color.BLACK);

        JButton addButton = new JButton("➕ Add Task");
        JButton removeButton = new JButton("🗑 Remove Task");
        completeButton = new JButton("✔ Complete Task");
        completeButton.setPreferredSize(new Dimension(200, 60)); 

        addButton.setBackground(new Color(0, 180, 0));
        removeButton.setBackground(new Color(180, 0, 0));
        completeButton.setBackground(new Color(0, 0, 180));

        addButton.setForeground(Color.BLACK);
        removeButton.setForeground(Color.BLACK);
        completeButton.setForeground(Color.BLACK);

        addButton.setFont(new Font("Verdana", Font.BOLD, 20)); 
        removeButton.setFont(new Font("Verdana", Font.BOLD, 20)); 
        completeButton.setFont(new Font("Verdana", Font.BOLD, 20)); 

        panel.add(taskField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(completeButton);

        frame.add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addTask());
        removeButton.addActionListener(e -> removeTask());
        completeButton.addActionListener(e -> completeTask());

    
        taskField.addActionListener(e -> addTask());

        
        taskList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    removeTask();
                }
            }
        });

        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskField.setText("");
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex);
        }
    }

    private void completeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String task = taskListModel.get(selectedIndex);
            if (!task.startsWith("[Completed] ")) {
                taskListModel.set(selectedIndex, "[Completed] " + task);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListApp::new);
    }
}



