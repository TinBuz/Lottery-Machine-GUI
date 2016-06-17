import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * LotteryTicketMachine holds all the GUI for choosing a 4, 5, 6 lottery ticket.
 * 
 * @author (Tin Buzancic) 
 * @version (5/4/2014)
 */
public class LotteryTicketMachine
{
    private JFrame frame;
    private JLabel statusLabel = new JLabel();
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();
    private ArrayList<LotteryTicket> tickets = new ArrayList<LotteryTicket>();

    public LotteryTicketMachine()
    {
        makeFrame();
    }

    /**
     * Quits the Lottery Machine pogram
     */
    private void quit()
    {
        System.exit(0);
    }

    /**
     * Help user interface.
     */
    private void instructions()
    {
        JOptionPane.showMessageDialog(frame, "Welcome to Lottery Machine t.0!\n"
            + "----------------------------------------------\n"
            + "Please choose either the Pick Four, Pick Five, or Pick Six option\n" 
            + "from the menu. Then choose between a manual entry lottery number\n"
            + "or a randomly generated quick pick. Good Luck!", "Lottery Machine t.0",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Creates the initial frame for the "Lottery Machine t.0"
     */
    private void makeFrame()
    {
        frame = new JFrame("Lottery Machine t.0");
        makeMenuBar(frame);
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        contentPane.setLayout(new BorderLayout(6, 6));

        JLabel welcomeStatus = new JLabel("Welcome to Lottery Machine t.0");
        contentPane.add(welcomeStatus, BorderLayout.NORTH);

        JPanel buttonToolbar = new JPanel();
        buttonToolbar.setLayout(new GridLayout(3,3,3,3));

        JButton pickFourButton = new JButton("PICK FOUR");
        buttonToolbar.add(pickFourButton);
        pickFourButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { pickFourTicket();}});
        buttonList.add(pickFourButton);

        JButton pickFiveButton = new JButton("PICK FIVE");
        buttonToolbar.add(pickFiveButton);
        pickFiveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { pickFiveTicket();}});
        buttonList.add(pickFiveButton);

        JButton pickSixButton = new JButton("PICK SIX");
        buttonToolbar.add(pickSixButton);
        pickSixButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { pickSixTicket();}});
        buttonList.add(pickSixButton);

        JPanel flow = new JPanel();
        flow.add(buttonToolbar);

        contentPane.add(flow, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Initiates the pick four branch of the lottery machine.
     */
    public void pickFourTicket()
    {
        frame = new JFrame("Pick Four");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        contentPane.setLayout(new BorderLayout(6, 6));

        JLabel welcomeStatus = new JLabel("Choose the method you wish in order to choose four numbers.");
        contentPane.add(welcomeStatus, BorderLayout.NORTH);

        JPanel secondToolbar = new JPanel();
        secondToolbar.setLayout(new GridLayout(1,1,1,1));

        JButton manualEntry = new JButton("MANUAL ENTRY");
        manualEntry.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { frame.dispose();  manualEntryTicket(new PickFour());}});
        secondToolbar.add(manualEntry);

        JButton quickPick = new JButton("QUICK PICK");
        quickPick.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { frame.dispose();  quickPickTicket(new PickFour());}});
        secondToolbar.add(quickPick);

        JPanel flow = new JPanel();
        flow.add(secondToolbar);
        contentPane.add(flow, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Initiates the pick five branch of the lottery machine.
     */
    public void pickFiveTicket()
    {
        frame = new JFrame("Pick Five");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        contentPane.setLayout(new BorderLayout(6, 6)); 

        JLabel welcomeStatus = new JLabel("Choose the method you wish in order to choose five numbers.");
        contentPane.add(welcomeStatus, BorderLayout.NORTH);

        JPanel secondToolbar = new JPanel();
        secondToolbar.setLayout(new GridLayout(1,1,1,1));

        JButton manualEntry = new JButton("MANUAL ENTRY");
        secondToolbar.add(manualEntry);
        manualEntry.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { frame.dispose();  manualEntryTicket(new PickFive());}});

        JButton quickPick = new JButton("QUICK PICK");
        secondToolbar.add(quickPick);
        quickPick.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { frame.dispose();  quickPickTicket(new PickFive());}});

        JPanel flow = new JPanel();
        flow.add(secondToolbar);
        contentPane.add(flow, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Initiates the pick six branch of the lottery machine.
     */
    public void pickSixTicket()
    {
        frame = new JFrame("Pick Six");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        contentPane.setLayout(new BorderLayout(6, 6));

        JLabel welcomeStatus = new JLabel("Choose the method you wish in order to choose six numbers.");
        contentPane.add(welcomeStatus, BorderLayout.NORTH);

        JPanel secondToolbar = new JPanel();
        secondToolbar.setLayout(new GridLayout(1,1,1,1));

        JButton manualEntry = new JButton("MANUAL ENTRY");
        secondToolbar.add(manualEntry);
        manualEntry.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { frame.dispose(); manualEntryTicket(new PickSix());}});

        JButton quickPick = new JButton("QUICK PICK");
        secondToolbar.add(quickPick);
        quickPick.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { frame.dispose();  quickPickTicket(new PickSix());}});

        JPanel flow = new JPanel();
        flow.add(secondToolbar);
        contentPane.add(flow, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    } 

    /**
     * creates a ticket based on manual input
     * @LotteryTicket The ticket you wish to create
     */

    public void manualEntryTicket(LotteryTicket ticket)
    {
        JPanel inputs = new JPanel();
        ArrayList<JTextField> fields = new ArrayList<JTextField>();
        inputs.setLayout(new GridLayout(1,0,7,7));
        for(int i = 0; i < ticket.getRandomNumber(); i++)
        {
            JTextField field = new JTextField();
            inputs.add(field);
            fields.add(field);
        }

        Object[] manObjects = {"Manual Entry", inputs};
        int result = JOptionPane.showConfirmDialog(frame, manObjects, "Manual Entry", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if(result == JOptionPane.OK_OPTION)
        {
            try
            {
                ArrayList<Integer> chosenNumbers = new ArrayList<Integer>();
                for(JTextField field : fields)
                {
                    int newInt = Integer.parseInt(field.getText());
                    chosenNumbers.add(newInt);
                }
                ticket.manualEntry(chosenNumbers);
                JOptionPane.showMessageDialog(frame, "SUCCESSFUL MANUAL TICKET ENTRY !" + ticket.listLotteryInfo(), "Manual Ticket Entry", JOptionPane.INFORMATION_MESSAGE);
                try(FileWriter writer = new FileWriter("F:/CHIEN LABS/BUZANCIC-lotteryMachine/textFiles/FinalTicket.txt"))
                {
                    writer.write(ticket.listLotteryInfo());
                }
                catch(IOException e)
                {
                    System.out.println("Could not print.");
                }
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(frame, "Number must be a valid digit.", "Manual Ticket Entry", JOptionPane.ERROR_MESSAGE);            
            }
            catch(ExceededLimitException e)
            {
                JOptionPane.showMessageDialog(frame, "Number must be between 1-60 AND and there must not be any duplicates", "Manual Ticket Entry", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if(result == JOptionPane.CANCEL_OPTION)
        {
            JOptionPane.showMessageDialog(frame, "OPERATION CANCELLED", "Lottery Machine t.0", JOptionPane.ERROR_MESSAGE);
        }   
    }

    /**
     * Creates a ticket based on a quick pick function
     * @param
     * LotteryTicket the ticket you wish to create.
     */

    private void quickPickTicket(LotteryTicket ticket)
    {
        ticket.quickPick();
        tickets.add(ticket);
        JOptionPane.showMessageDialog(frame, "SUCCESSFUL QUICK PICK ENTRY" + ticket.listLotteryInfo(), "Quick Pick", JOptionPane.INFORMATION_MESSAGE);
        try(FileWriter writer = new FileWriter("F:/CHIEN LABS/BUZANCIC-lotteryMachine/textFiles/FinalTicket.txt"))
        {
            writer.write(ticket.listLotteryInfo());
        }
        catch(IOException e)
        {
            System.out.println("Could not print.");
        }

    }

    /**
     * creates a menu bar for a desired frame.
     * @param
     * JFrame the frame you wish to add a menu bar to.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenuItem quitMenu = new JMenuItem("Quit");
        menuBar.add(quitMenu);
        quitMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { quit(); }
            });

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem aboutItem= new JMenuItem("Instructions");
        aboutItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { instructions(); }
            });
        helpMenu.add(aboutItem);

        frame.pack();
        frame.setVisible(true);   
    }

}
