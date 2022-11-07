import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class TextEditor extends JFrame implements ActionListener, KeyListener {
    JFrame frame;
    JTextArea text;
    Set<String> data1;
    TextEditor()
    {
        //created frame
        frame=new JFrame("Text Editor");
        frame.setSize(500,500);

        //auto suggestion
        data1 = new TreeSet<String>();
        String[] userdata = {"chethan","is","the","person","who","has","struggled","lot","in","life"};
        for(String i:userdata)
        {
            data1.add(i);
        }

        //created text area
        text = new JTextArea();


        frame.add(text);

        frame.setVisible(true);

        //created Menubar
        JMenuBar menuBar = new JMenuBar();

        //created file, edit and close menu
        JMenu fileMenu = new JMenu("File Menu");
        JMenu editMenu = new JMenu("Edit Menu");
        JMenu Close = new JMenu("Close");
        JMenu Theme = new JMenu("Theme");


        //added to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(Theme);
        menuBar.add(Close);

        //creating menu items in file menu
        JMenuItem Open = new JMenuItem("Open File");
        JMenuItem Save = new JMenuItem("Save File");
        JMenuItem Print = new JMenuItem("Print File");
        JMenuItem New = new JMenuItem("New File");
        JMenuItem Dark = new JMenuItem("Dark");
        JMenuItem Light = new JMenuItem("Light");

        //added items to filemenu
        fileMenu.add(Open);
        fileMenu.add(Save);
        fileMenu.add(Print);
        fileMenu.add(New);

        //add items to Theme
        Theme.add(Dark);
        Theme.add(Light);

        //action for item
        Open.addActionListener(this);
        Save.addActionListener(this);
        Print.addActionListener(this);
        New.addActionListener(this);

        //created jmenuitem for editmenu
        JMenuItem Cut = new JMenuItem("Cut");
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");
        JMenuItem FontSize = new JMenuItem("FontSize");

        //action for item
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Dark.addActionListener(this);
        Light.addActionListener(this);
        FontSize.addActionListener(this);

        //added item to edit menu
        editMenu.add(Cut);
        editMenu.add(Copy);
        editMenu.add(Paste);
        editMenu.add(FontSize);
        text.addKeyListener(this);
        //creating a close window item in close menu
        JMenuItem CloseWindow = new JMenuItem("Close Window");
        Close.add(CloseWindow);
        //closewindow actionlistener
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
        else if(s == "Close Window")
        {
            //completely closes the program not only the frame
            frame.dispose();

        }
        else if (s=="FontSize")
        {
            String sizeOfFont = JOptionPane.showInputDialog(null,"Enter Font Size",JOptionPane.OK_CANCEL_OPTION);
            if (sizeOfFont != null){
                int convertSizeOfFont = Integer.parseInt(sizeOfFont);
                Font font = new Font(Font.SANS_SERIF,Font.PLAIN,convertSizeOfFont);
                text.setFont(font);
            }
        }
        else if(s=="Dark")
        {
            text.setBackground(Color.DARK_GRAY);        //dark Theme
            text.setForeground(Color.WHITE);
        }
        else if (s=="Light")
        {
            text.setBackground(Color.WHITE);        //dark Theme
            text.setForeground(Color.BLACK);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
         String checktext = text.getText();
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE||e.getKeyCode()==KeyEvent.VK_DELETE)
        {

        }
        else
        {
            String to_check=text.getText();
            int to_check_len=to_check.length();
            for(String data:data1)
            {
                String check_from_data="";
                for(int i=0;i<to_check_len;i++)
                {
                    if(to_check_len<=data.length())
                    {
                        check_from_data = check_from_data+data.charAt(i);
                    }
                }
                //System.out.print(check_from_data);
                if(check_from_data.equals(to_check))
                {
                    //System.out.print("Found");
                    text.setText(data);
                    text.setSelectionStart(to_check_len);
                    text.setSelectionEnd(data.length());
                    break;
                }
            }
        }
    }
}
