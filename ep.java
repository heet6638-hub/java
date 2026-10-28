
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ep extends JFrame
{
    JTextField idField, nameField, salaryField;
    JComboBox<String> departmentBox;
    JTextArea outputArea;

    ArrayList<Employee> employees = new ArrayList<Employee>();
    File file = new File("C:\\javapractice\\employees.txt");

    Color bg = new Color(242, 246, 249);
    Color dark = new Color(20, 40, 52);
    Color teal = new Color(15, 118, 110);
    Color green = new Color(22, 163, 117);
    Color blue = new Color(59, 130, 246);
    Color purple = new Color(139, 92, 246);
    Color orange = new Color(234, 139, 55);
    Color text = new Color(45, 62, 75);

    ep()
    {
        setTitle("Employee Payroll System");
        setSize(850, 700);
        setMinimumSize(new Dimension(760, 620));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(bg);

        // HEADER
        GradientPanel header = new GradientPanel(
            new Color(20, 40, 52),
            new Color(15, 118, 110)
        );

        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(24, 28, 24, 28));

        JPanel heading = new JPanel();
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.setOpaque(false);

        JLabel title = new JLabel("EMPLOYEE PAYROLL");
        title.setFont(new Font("Segoe UI", Font.BOLD, 27));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel(
            "Employee management  |  Salary calculation"
        );
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(210, 235, 232));

        heading.add(title);
        heading.add(Box.createVerticalStrut(6));
        heading.add(subtitle);

        JLabel badge = new JLabel("  JAVA DESKTOP APP  ");
        badge.setFont(new Font("Segoe UI", Font.BOLD, 12));
        badge.setForeground(Color.WHITE);
        badge.setBorder(new CompoundBorder(
            new LineBorder(new Color(130, 210, 195)),
            new EmptyBorder(8, 5, 8, 5)
        ));

        header.add(heading, BorderLayout.WEST);
        header.add(badge, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // MAIN PANEL
        JPanel main = new JPanel(new BorderLayout(0, 16));
        main.setBackground(bg);
        main.setBorder(new EmptyBorder(20, 24, 18, 24));

        // EMPLOYEE FORM CARD
        JPanel formCard = new JPanel(new BorderLayout(0, 15));
        formCard.setBackground(Color.WHITE);
        formCard.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 229, 234)),
            new EmptyBorder(18, 20, 18, 20)
        ));

        JPanel formHeading = new JPanel(new BorderLayout());
        formHeading.setBackground(Color.WHITE);

        JLabel formTitle = new JLabel("Employee Details");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 19));
        formTitle.setForeground(dark);

        JLabel formNote = new JLabel("Enter employee information below");
        formNote.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formNote.setForeground(new Color(120, 135, 145));

        JPanel titleGroup = new JPanel();
        titleGroup.setLayout(new BoxLayout(titleGroup, BoxLayout.Y_AXIS));
        titleGroup.setBackground(Color.WHITE);
        titleGroup.add(formTitle);
        titleGroup.add(Box.createVerticalStrut(4));
        titleGroup.add(formNote);

        formHeading.add(titleGroup, BorderLayout.WEST);
        formCard.add(formHeading, BorderLayout.NORTH);

        // INPUT FIELDS
        JPanel form = new JPanel(new GridLayout(4, 2, 15, 12));
        form.setBackground(Color.WHITE);

        idField = new JTextField();
        nameField = new JTextField();
        salaryField = new JTextField();

        departmentBox = new JComboBox<String>(
            new String[]{"IT", "HR", "Finance", "Sales"}
        );

        styleField(idField);
        styleField(nameField);
        styleField(salaryField);

        departmentBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        departmentBox.setBackground(new Color(249, 251, 252));
        departmentBox.setPreferredSize(new Dimension(200, 38));

        form.add(makeLabel("Employee ID"));
        form.add(idField);

        form.add(makeLabel("Employee Name"));
        form.add(nameField);

        form.add(makeLabel("Basic Salary (Rs.)"));
        form.add(salaryField);

        form.add(makeLabel("Department"));
        form.add(departmentBox);

        formCard.add(form, BorderLayout.CENTER);

        // COLOURED BUTTONS
        JButton addButton = new JButton("Add Employee");
        JButton saveButton = new JButton("Save Records");
        JButton loadButton = new JButton("Load Records");
        JButton viewButton = new JButton("View All");

        styleButton(addButton, green);
        styleButton(saveButton, blue);
        styleButton(loadButton, purple);
        styleButton(viewButton, orange);

        JPanel buttons = new JPanel(new GridLayout(1, 4, 10, 0));
        buttons.setBackground(Color.WHITE);

        buttons.add(addButton);
        buttons.add(saveButton);
        buttons.add(loadButton);
        buttons.add(viewButton);

        formCard.add(buttons, BorderLayout.SOUTH);

        main.add(formCard, BorderLayout.NORTH);

        // EMPLOYEE RECORDS CARD
        JPanel outputCard = new JPanel(new BorderLayout(0, 12));
        outputCard.setBackground(Color.WHITE);
        outputCard.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 229, 234)),
            new EmptyBorder(16, 20, 18, 20)
        ));

        JPanel outputHeading = new JPanel(new BorderLayout());
        outputHeading.setBackground(Color.WHITE);

        JLabel outputTitle = new JLabel("Payroll Summary");
        outputTitle.setFont(new Font("Segoe UI", Font.BOLD, 19));
        outputTitle.setForeground(dark);

        JLabel outputTag = new JLabel("  RECORDS  ");
        outputTag.setFont(new Font("Segoe UI", Font.BOLD, 11));
        outputTag.setForeground(teal);
        outputTag.setBorder(new LineBorder(new Color(190, 225, 218)));

        outputHeading.add(outputTitle, BorderLayout.WEST);
        outputHeading.add(outputTag, BorderLayout.EAST);

        outputCard.add(outputHeading, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setForeground(new Color(220, 240, 238));
        outputArea.setBackground(new Color(25, 39, 49));
        outputArea.setCaretColor(Color.WHITE);
        outputArea.setMargin(new Insets(15, 15, 15, 15));
        outputArea.setText(
            "  WELCOME TO EMPLOYEE PAYROLL\n\n" +
            "  Add an employee using the form above.\n" +
            "  Click View All to display employee details."
        );

        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        outputCard.add(scroll, BorderLayout.CENTER);
        main.add(outputCard, BorderLayout.CENTER);

        add(main, BorderLayout.CENTER);

        // FOOTER
        JLabel footer = new JLabel(
            "Employee Payroll System  |  Java Swing  |  File Handling"
        );
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setForeground(new Color(110, 125, 135));
        footer.setBorder(new EmptyBorder(0, 25, 12, 25));

        add(footer, BorderLayout.SOUTH);

        // BUTTON ACTIONS
        addButton.addActionListener(e -> addEmployee());
        saveButton.addActionListener(e -> saveEmployees());

        loadButton.addActionListener(e -> {
            loadEmployees(true);
            showEmployees();
        });

        viewButton.addActionListener(e -> showEmployees());

        // Load saved data without displaying it immediately
        loadEmployees(false);

        outputArea.setText(
            "  WELCOME TO EMPLOYEE PAYROLL\n\n" +
            "  Add an employee using the form above.\n" +
            "  Click View All to display employee details."
        );

        setVisible(true);
    }

    // GRADIENT HEADER
    static class GradientPanel extends JPanel
    {
        Color c1, c2;

        GradientPanel(Color c1, Color c2)
        {
            this.c1 = c1;
            this.c2 = c2;
        }

        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setPaint(new GradientPaint(
                0, 0, c1, getWidth(), getHeight(), c2
            ));

            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }

    // LABEL DESIGN
    JLabel makeLabel(String value)
    {
        JLabel label = new JLabel(value);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(text);
        return label;
    }

    // TEXT FIELD DESIGN
    void styleField(JTextField field)
    {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(text);
        field.setBackground(new Color(249, 251, 252));

        field.setBorder(new CompoundBorder(
            new LineBorder(new Color(205, 219, 225)),
            new EmptyBorder(8, 10, 8, 10)
        ));

        field.setPreferredSize(new Dimension(200, 38));
    }

    // BUTTON DESIGN
    void styleButton(JButton button, Color color)
    {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
        button.setPreferredSize(new Dimension(150, 42));
    }

    // ADD EMPLOYEE
    void addEmployee()
    {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String salaryText = salaryField.getText().trim();
        String department = (String) departmentBox.getSelectedItem();

        if (id.isEmpty() || name.isEmpty() || salaryText.isEmpty())
        {
            JOptionPane.showMessageDialog(
                this, "Please fill in all fields."
            );
            return;
        }

        try
        {
            double salary = Double.parseDouble(salaryText);

            if (salary < 0)
            {
                JOptionPane.showMessageDialog(
                    this, "Salary cannot be negative."
                );
                return;
            }

            for (Employee emp : employees)
            {
                if (emp.id.equalsIgnoreCase(id))
                {
                    JOptionPane.showMessageDialog(
                        this, "Employee ID already exists."
                    );
                    return;
                }
            }

            Employee emp = new Employee(
                id, name, salary, department
            );

            employees.add(new Employee(emp));

            idField.setText("");
            nameField.setText("");
            salaryField.setText("");

            showEmployees();

            JOptionPane.showMessageDialog(
                this, "Employee added. Click Save Records to store it."
            );
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(
                this, "Enter salary as a number."
            );
        }
    }

    // SAVE EMPLOYEE RECORDS
    void saveEmployees()
    {
        try
        {
            File folder = file.getParentFile();

            if (!folder.exists())
                folder.mkdirs();

            try (FileWriter fw = new FileWriter(file))
            {
                for (Employee emp : employees)
                {
                    fw.write(
                        emp.id.replace("|", " ") + "|" +
                        emp.name.replace("|", " ") + "|" +
                        emp.basicSalary + "|" +
                        emp.department + "\n"
                    );
                }
            }

            JOptionPane.showMessageDialog(
                this,
                "Records saved successfully!\n" +
                file.getAbsolutePath()
            );
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(
                this, "File save error: " + ex.getMessage()
            );
        }
    }

    // LOAD EMPLOYEE RECORDS
    void loadEmployees(boolean message)
    {
        ArrayList<Employee> list = new ArrayList<Employee>();

        if (!file.exists())
        {
            employees.clear();

            if (message)
            {
                JOptionPane.showMessageDialog(
                    this,
                    "No saved file found yet.\n" +
                    file.getAbsolutePath()
                );
            }

            return;
        }

        try (BufferedReader br = new BufferedReader(
            new FileReader(file)))
        {
            String line;

            while ((line = br.readLine()) != null)
            {
                String[] data = line.split("\\|", -1);

                if (data.length == 4)
                {
                    double salary = Double.parseDouble(data[2]);

                    list.add(new Employee(
                        data[0], data[1], salary, data[3]
                    ));
                }
            }

            employees.clear();
            employees.addAll(list);

            if (message)
            {
                JOptionPane.showMessageDialog(
                    this, "Records loaded successfully."
                );
            }
        }
        catch (IOException | NumberFormatException ex)
        {
            if (message)
            {
                JOptionPane.showMessageDialog(
                    this, "Could not load records: " + ex.getMessage()
                );
            }
        }
    }

    // DISPLAY EMPLOYEES AND SALARY
    void showEmployees()
    {
        outputArea.setText("");

        if (employees.isEmpty())
        {
            outputArea.setText(
                "  NO EMPLOYEE RECORDS FOUND\n\n" +
                "  Add an employee and click Save Records.\n\n" +
                "  File: " + file.getAbsolutePath()
            );
            return;
        }

        for (Employee emp : employees)
        {
            outputArea.append(
                "\n  EMPLOYEE ID    : " + emp.id + "\n"
            );

            outputArea.append(
                "  NAME           : " + emp.name + "\n"
            );

            outputArea.append(
                "  DEPARTMENT     : " + emp.department + "\n"
            );

            outputArea.append(
                "  BASIC SALARY   : Rs. " + emp.basicSalary + "\n"
            );

            outputArea.append(
                "  ALLOWANCE (20%): Rs. " + emp.getAllowance() + "\n"
            );

            outputArea.append(
                "  DEDUCTION (10%): Rs. " + emp.getDeduction() + "\n"
            );

            outputArea.append(
                "  NET SALARY     : Rs. " + emp.getNetSalary() + "\n"
            );

            outputArea.append(
                "\n  ----------------------------------------\n"
            );
        }
    }

    // EMPLOYEE CLASS
    static class Employee
    {
        String id, name, department;
        double basicSalary;

        // Default constructor
        Employee()
        {
            id = "";
            name = "";
            basicSalary = 0;
            department = "";
        }

        // Parameterized constructor
        Employee(String id, String name,
                 double basicSalary, String department)
        {
            this.id = id;
            this.name = name;
            this.basicSalary = basicSalary;
            this.department = department;
        }

        // Copy constructor
        Employee(Employee e)
        {
            this(e.id, e.name, e.basicSalary, e.department);
        }

        double getAllowance()
        {
            return basicSalary * 0.20;
        }

        double getDeduction()
        {
            return basicSalary * 0.10;
        }

        double getNetSalary()
        {
            return basicSalary + getAllowance() - getDeduction();
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new ep());
    }
}