
package com.student.ui;

import com.student.dao.StudentDAO;
import com.student.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;

public class StudentManagementUI extends JFrame {

    private final StudentDAO studentDAO = new StudentDAO();

    private final JTextField idField = new JTextField(10);
    private final JTextField nameField = new JTextField(20);
    private final JTextField ageField = new JTextField(10);
    private final JTextField emailField = new JTextField(20);
    private final JTextField courseField = new JTextField(20);

    private final DefaultTableModel tableModel =
            new DefaultTableModel(
                    new String[]{"ID", "Name", "Age", "Email", "Course"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

    private final JTable studentTable = new JTable(tableModel);


    private final JTextField searchField = new JTextField(20);
    private final JButton searchButton = new JButton("Search");
    private final JButton showAllButton = new JButton("Show All");
    private final JButton refreshButton = new JButton("Refresh");


    public StudentManagementUI() {
        setTitle("Student Management System");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buildUI();
        loadStudents();
    }

    private void buildUI() {
        JPanel formPanel = new JPanel(new GridBagLayout());


        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        );

        formPanel.setBorder(
                BorderFactory.createTitledBorder("Student Details")
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addField(formPanel, gbc, 0, "Student ID:", idField);
        addField(formPanel, gbc, 1, "Name:", nameField);
        addField(formPanel, gbc, 2, "Age:", ageField);
        addField(formPanel, gbc, 3, "Email:", emailField);
        addField(formPanel, gbc, 4, "Course:", courseField);

        idField.setEditable(false);

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");


        styleButton(addButton, new Color(46, 125, 50));
        styleButton(updateButton, new Color(25, 118, 210));
        styleButton(deleteButton, new Color(198, 40, 40));
        styleButton(clearButton, new Color(97, 97, 97));

        styleButton(searchButton, new Color(25, 118, 210));
        styleButton(showAllButton, new Color(97, 97, 97));
        styleButton(refreshButton, new Color(0, 121, 107));


        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);


        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);


        studentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        studentTable.setRowHeight(25);
        studentTable.setFillsViewportHeight(true);
        studentTable.setAutoCreateRowSorter(true);

        studentTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Student Records")
        );


        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        searchPanel.add(new JLabel("Search by name or course:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);
        searchPanel.add(refreshButton);


        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearFields());

        searchButton.addActionListener(e -> searchStudents());
        searchField.addActionListener(e -> searchStudents());


        showAllButton.addActionListener(e -> {
            searchField.setText("");
            loadStudents();
        });

        refreshButton.addActionListener(e -> {
            searchField.setText("");
            loadStudents();
        });


        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()
                    && studentTable.getSelectedRow() != -1) {
                int viewRow = studentTable.getSelectedRow();
                int row = studentTable.convertRowIndexToModel(viewRow);

                idField.setText(
                        tableModel.getValueAt(row, 0).toString()
                );
                nameField.setText(
                        tableModel.getValueAt(row, 1).toString()
                );
                ageField.setText(
                        tableModel.getValueAt(row, 2).toString()
                );
                emailField.setText(
                        tableModel.getValueAt(row, 3).toString()
                );
                courseField.setText(
                        tableModel.getValueAt(row, 4).toString()
                );
            }
        });
    }


    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        );
        button.setOpaque(true);
    }


    private void addField(JPanel panel, GridBagConstraints gbc,
                          int row, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    private void loadStudents() {
        tableModel.setRowCount(0);

        List<Student> students = studentDAO.getAllStudents();

        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getEmail(),
                    student.getCourse()
            });
        }
    }


    private void searchStudents() {
        String keyword = searchField.getText()
                .trim()
                .toLowerCase();

        tableModel.setRowCount(0);

        List<Student> students = studentDAO.getAllStudents();

        for (Student student : students) {
            boolean matchesName =
                    student.getName().toLowerCase().contains(keyword);

            boolean matchesCourse =
                    student.getCourse().toLowerCase().contains(keyword);

            if (matchesName || matchesCourse) {
                tableModel.addRow(new Object[]{
                        student.getId(),
                        student.getName(),
                        student.getAge(),
                        student.getEmail(),
                        student.getCourse()
                });
            }
        }
    }



    private boolean isValidEmail(String email) {
        return Pattern.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                email
        );
    }


    private void addStudent() {
        try {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String email = emailField.getText().trim();
            String course = courseField.getText().trim();

            if (name.isEmpty() || ageText.isEmpty()
                    || email.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please fill in all fields."
                );
                return;
            }



            if (!name.matches("[\\p{L}][\\p{L} .'-]*")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid student name."
                );
                return;
            }


            if (!course.matches("[\\p{L}][\\p{L}0-9 &().'-]*")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid course name."
                );
                return;
            }



            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(
                        this, "Please enter a valid email address."
                );
                return;
            }

            int age = Integer.parseInt(ageText);



            if (age < 1 || age > 120)  {
                JOptionPane.showMessageDialog(
                        this, "Age must be between 1 and 120."
                );
                return;
            }

            Student student = new Student(
                    name, age, email, course
            );

            if (studentDAO.addStudent(student)) {
                JOptionPane.showMessageDialog(
                        this, "Student added successfully!"
                );
                loadStudents();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Could not add student. Check the email and database connection."
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, "Age must be a valid whole number."
            );
        }
    }

    private void updateStudent() {
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please select a student from the table."
            );
            return;
        }

        try {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String email = emailField.getText().trim();
            String course = courseField.getText().trim();

            if (name.isEmpty() || ageText.isEmpty()
                    || email.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please fill in all fields."
                );
                return;
            }


            if (!name.matches("[\\p{L}][\\p{L} .'-]*")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid student name."
                );
                return;
            }


            if (!course.matches("[\\p{L}][\\p{L}0-9 &().'-]*")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid course name."
                );
                return;
            }



            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(
                        this, "Please enter a valid email address."
                );
                return;
            }

            int id = Integer.parseInt(idField.getText());
            int age = Integer.parseInt(ageText);


            if (age < 1 || age > 120) {
                JOptionPane.showMessageDialog(
                        this, "Age must be between 1 and 120."
                );
                return;
            }

            Student student = new Student(
                    id, name, age, email, course
            );

            if (studentDAO.updateStudent(student)) {
                JOptionPane.showMessageDialog(
                        this, "Student updated successfully!"
                );
                loadStudents();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(
                        this, "Student was not updated."
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, "Age must be a valid whole number."
            );
        }
    }

    private void deleteStudent() {
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please select a student to delete."
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int id = Integer.parseInt(idField.getText());

        if (studentDAO.deleteStudent(id)) {
            JOptionPane.showMessageDialog(
                    this, "Student deleted successfully!"
            );
            loadStudents();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(
                    this, "Student could not be deleted."
            );
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        emailField.setText("");
        courseField.setText("");
        studentTable.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementUI ui = new StudentManagementUI();
            ui.setVisible(true);
        });
    }
}
