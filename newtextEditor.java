import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class newtextEditor extends JFrame implements ActionListener, MenuListener {
    JFrame frame;
    JTextArea text;
    newtextEditor()
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

        //created file and edit menu
        JMenu fileMenu = new JMenu("File Menu");
        JMenu editMenu = new JMenu("Edit Menu");
        JMenu Close = new JMenu("Close");

        //added to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(Close);

        //JMenuItem CloseWindow = new JMenuItem("Close Window");

        Close.addMenuListener(this);
        //close actionlistener
        Close.addActionListener(this);

        JMenuItem Open = new JMenuItem("Open File");
        JMenuItem Save = new JMenuItem("Save File");
        JMenuItem Print = new JMenuItem("Print File");
        JMenuItem New = new JMenuItem("New File");

        //added items to filemenu
        fileMenu.add(Open);
        fileMenu.add(Save);
        fileMenu.add(Print);
        fileMenu.add(New);

        //action for item
        Open.addActionListener(this);
        Save.addActionListener(this);
        Print.addActionListener(this);
        New.addActionListener(this);

        //created jmenuitem for editmenu
        JMenuItem Cut = new JMenuItem("Cut");
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");

        //action for item
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);


        //added item to edit menu
        editMenu.add(Cut);
        editMenu.add(Copy);
        editMenu.add(Paste);


        frame.setJMenuBar(menuBar);
        frame.show();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args)
    {
        newtextEditor editor = new newtextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s=="Cut")
        {
            text.cut();
        }
        else if(s=="Copy")
        {
            text.copy();
        }
        else if(s=="Paste") {
            text.paste();
        }
        else if(s=="Print File")
        {
            try {
                text.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s=="New File")
        {
            text.setText("");
        }
        else if(s=="Open File")
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
        else if (s=="Save File")
        {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
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
        else if(s == "Close Window")
        {
            //completely closes the program not only the frame
            frame.dispose();

        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        frame.dispose();
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
