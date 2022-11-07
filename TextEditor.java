import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.print.PrinterException;
import java.io.*;


public class TextEditor extends JFrame implements ActionListener {
    JFrame frame;
    JTextArea text;

    TextEditor()
    {
        //created frame
        frame=new JFrame("Text Editor");
        frame.setSize(500,500);

        //created text area
        text = new JTextArea();

        frame.add(text);

        frame.setVisible(true);

        //created Menubar
        JMenuBar menuBar = new JMenuBar();

        //created file, edit,theme and close menu
        JMenu fileMenu = new JMenu("File Menu");
        JMenu editMenu = new JMenu("Edit Menu");
        JMenu Theme = new JMenu("Theme");
        JMenu Close = new JMenu("Close");

        //added to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(Theme);
        menuBar.add(Close);

        //creating menu items
        JMenuItem Open = new JMenuItem("Open File");
        JMenuItem Save = new JMenuItem("Save File");
        JMenuItem Print = new JMenuItem("Print File");
        JMenuItem New = new JMenuItem("New File");
        //created jmenuitem for editmenu
        JMenuItem Cut = new JMenuItem("Cut");
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");
        JMenuItem FontSize = new JMenuItem("FontSize");
        //created jmenuitem for theme
        JMenuItem Dark = new JMenuItem("Dark");
        JMenuItem Light = new JMenuItem("Light");
        //creating a close window item in close menu
        JMenuItem CloseWindow = new JMenuItem("Close Window");


        //added items to filemenu
        fileMenu.add(Open);
        fileMenu.add(Save);
        fileMenu.add(Print);
        fileMenu.add(New);

        //added item to edit menu
        editMenu.add(Cut);
        editMenu.add(Copy);
        editMenu.add(Paste);
        editMenu.add(FontSize);

        //add items to Theme
        Theme.add(Dark);
        Theme.add(Light);

        //add items to CLOSE
        Close.add(CloseWindow);

        //action for menuitem
        Open.addActionListener(this);
        Save.addActionListener(this);
        Print.addActionListener(this);
        New.addActionListener(this);
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Dark.addActionListener(this);
        Light.addActionListener(this);
        FontSize.addActionListener(this);
        CloseWindow.addActionListener(this);

        frame.setJMenuBar(menuBar);
        frame.show();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args)
    {
        new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Cut"))
        {
            text.cut();
        }
        else if(s.equals("Copy"))
        {
            text.copy();
        }
        else if(s.equals("Paste")) {
            text.paste();
        }
        else if(s.equals("Print File"))
        {
            try {
                text.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s.equals("New File"))
        {
            text.setText("");
        }
        else if(s.equals("Open File"))
        {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String s1="",s2="";
                    s1=bufferedReader.readLine();
                    while (s1!=null)
                    {
                        s2+=s1+"\n";
                        s1=bufferedReader.readLine();
                    }
                    text.setText(s2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        else if (s.equals("Save File"))
        {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showSaveDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                    writer.write(text.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s.equals("Close Window"))
        {
            //completely closes the program not only the frame
            frame.dispose();

        }
        else if (s.equals("FontSize"))
        {
            String sizeOfFont = JOptionPane.showInputDialog(null,"Enter Font Size",JOptionPane.OK_CANCEL_OPTION);
            if (sizeOfFont != null){
                int convertSizeOfFont = Integer.parseInt(sizeOfFont);
                Font font = new Font(Font.SANS_SERIF,Font.PLAIN,convertSizeOfFont);
                text.setFont(font);
            }
        }
        else if(s.equals("Dark"))
        {
            text.setBackground(Color.DARK_GRAY);        //dark Theme
            text.setForeground(Color.WHITE);
        }
        else if (s.equals("Light"))
        {
            text.setBackground(Color.WHITE);        //light Theme
            text.setForeground(Color.BLACK);
        }
    }
}
