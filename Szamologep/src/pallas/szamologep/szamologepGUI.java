package pallas.szamologep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class szamologepGUI implements ActionListener{
	
	JFrame frame;
	JTextField kijelzo;
	JTextField kiskijelzo;
	JPanel panel;
	JPanel textFieldsPanel;
	JButton gomb;
	JButton torlesGomb;
	String input;
	boolean kiszamolva = false;
	boolean idiot = false;
	
	szamologepGUI() {
	createFrame();
	}
	
	//JTextField (kiskijelzo) beállításai
	public void kiskijelzoSettings() {
		kiskijelzo = new JTextField();
		kiskijelzo.setPreferredSize(new Dimension(400, 30));
		kiskijelzo.setFont(new Font ("Arial", Font.ITALIC, 15));
		kiskijelzo.setForeground(new Color(218, 165, 32));
		kiskijelzo.setBackground(new Color(75, 0, 130));
		kiskijelzo.setBorder(null);
		kiskijelzo.setEditable(false);
	}
	//JTextField (kijelzo) beállításai
	public void kijelzoSettings() {
		kijelzo = new JTextField();
		kijelzo.setPreferredSize(new Dimension(400, 40));
		kijelzo.setFont(new Font ("Oswald", Font.PLAIN, 30));
		kijelzo.setForeground(new Color(218, 165, 32));
		kijelzo.setBackground(new Color(75, 0, 130));
		kijelzo.setBorder(null);
		kijelzo.setEditable(true);
		kijelzo.setText("0");
	}
	//JButton (gomb) beállításai
	public void buttonSettings() {
		gomb.setFont(new Font("Oswald", Font.PLAIN, 20));
		gomb.setBackground(Color.LIGHT_GRAY);
		gomb.setForeground(new Color(255, 69, 0));
	}
	//JButton (torlesGomb) beállításai
/*	public void torlesgombSettings() {
		torlesGomb.setFont(new Font("Oswald", Font.PLAIN, 30));
		torlesGomb.setBackground(Color.LIGHT_GRAY);
		torlesGomb.setForeground(new Color(255, 69, 0));
	} */
	//JPanel (panel) beállításai
	public void panelSettings() {
		panel = new JPanel();
		panel.setBackground(new Color(65,105,225));
		panel.setLayout(new GridLayout(5, 4, 1, 1));

	    String[] buttonLabels = {
	        
	    	"7", "8", "9", "/",
	        "4", "5", "6", "*",
	        "1", "2", "3", "-",
	        "" , "0", "" , "+", 
	        "C","<" ,"DEL","="
	        
	    };

	    for (String label : buttonLabels) {
	   /* 	if (label.equals("<")) {
	    		torlesGomb = new JButton("\u2190");
	    		torlesGomb.addActionListener(this);
	    		torlesgombSettings();
	    		panel.add(torlesGomb);
	    	} else */ if (!label.isEmpty()) {
	    		gomb = new JButton(label);
	    		gomb.addActionListener(this);
	    		buttonSettings();
	    		panel.add(gomb);
	    	} else {
	    		JPanel space = new JPanel();
	    		space.setBackground(new Color(65,105,225));
	    		panel.add(space);
	    	}
	    }
	}
	//kijelzo és kiskijelzo egybe Panel-ban
	public void textFieldsPanel() {
		textFieldsPanel = new JPanel(new BorderLayout());
		kiskijelzoSettings();
		kijelzoSettings();
		textFieldsPanel.add(kiskijelzo, BorderLayout.NORTH); 
		textFieldsPanel.add(kijelzo, BorderLayout.CENTER);
	}
	//JFrame (frame) létrehozása és beállításai
	public void createFrame () {
		frame = new JFrame();
		frame.setTitle("Számológép");
		frame.getContentPane().setBackground(Color.gray);
		frame.setLocationRelativeTo(null);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textFieldsPanel();
		panelSettings();

	    frame.setLayout(new BorderLayout());
	    frame.add(panel, BorderLayout.CENTER);
	    frame.add(textFieldsPanel, BorderLayout.NORTH);

	    frame.revalidate();
    }
	// "=" gombhoz, műveletek kiszámítása metódus
	public String szamolgatas(String muvelet) {  

	        // Muveletek szetvalogatasa szam + muvelet + szam - ra
			String[] reszek = muvelet.split(" ");
	        int kiskijelzoSzam = Integer.parseInt(reszek[0]);
	        String operator = reszek[1];
	        int kijelzoSzam = Integer.parseInt(reszek[2]);
	            // Mivan ha +, - , *, /
	            switch (operator) {
	                case "+":
	                	kiskijelzoSzam += kijelzoSzam;
	                    break;
	                case "-":
	                	kiskijelzoSzam -= kijelzoSzam;
	                    break;
	                case "*":
	                	kiskijelzoSzam *= kijelzoSzam;
	                    break;
	                case "/":
	                    if (kiskijelzoSzam != 0) {
	                    	kiskijelzoSzam /= kijelzoSzam;
	                    } else {
	                    	//Ha a "kiskijelzoSzam" erteke 0, akkor hibaüzenet (nem osztunk 0-val)
	                         return "nemjau";
	                    }
	                    break;
	            }
	            return Integer.toString(kiskijelzoSzam);
	}
	//Eksönz
	public void actionPerformed(ActionEvent e) {
		String gombnyomas = e.getActionCommand();
		input = kijelzo.getText();
		
	switch (gombnyomas) {
	
		case "C":
			//0-val osztas utan torli a kepernyot
			if (idiot) {
        		kiskijelzo.setText("");
        		kijelzo.setText("0");
        		idiot = false;
        	//"kijelzo" 0-ra allitasa
			} else {
	        kijelzo.setText("0");
			}
			break;
			
        case "<":
        	//0-val osztas utan torli a kepernyot
        	if (idiot) {
        		kiskijelzo.setText("");
        		kijelzo.setText("0");
        		idiot = false;
        	//Az utolso szamjegyet torli ha tobb mint 1 szamjegy van a "kijelzo"-n
        	} else if (!kijelzo.getText().equals("0") && kijelzo.getText().length() > 1) {
                input = kijelzo.getText().substring(0, kijelzo.getText().length() - 1);
                kijelzo.setText(input);
            //Ha egy db szamjegy van a "kijelzo"-n 0-ra allitja
            } else {
           	    kijelzo.setText("0");
            }
            break;
            
        case "DEL":
        		//Alapra allit mindent
    			kijelzo.setText("0");
        		kiskijelzo.setText("");
        		break;
            
        case "=":
          try {
        	//0-val osztas utan torli a kepernyot
        	if (idiot) {
        		kiskijelzo.setText("");
        		kijelzo.setText("0");
        		kiszamolva = true;
        		idiot = false;
        	//Kiszamolt eredmeny utan kinthagyja az eredmenyt a "kijelzo"-n esetleges tovabbi felhasznalas celjabol
        	} else if (kiszamolva) {
        		kiskijelzo.setText("");
        		kijelzo.setText(kijelzo.getText());
        	//Kiszamolja a letrehozott "szamolgatas" metodus alapjan az eredmenyt
        	} else if (!kijelzo.getText().isEmpty() && !kiskijelzo.getText().isEmpty()) {
                String muvelet = kiskijelzo.getText() + kijelzo.getText();
                String eredmeny = szamolgatas(muvelet);
                //Ha 0-val osztas van hiba!
                if(eredmeny.equals("nemjau")) {
                	kiskijelzo.setText("Barom!");
                    kijelzo.setText("Nullaval nem osztunk!!");
                    idiot = true;
                } else {
                	kiskijelzo.setText(muvelet + " =");
                	kijelzo.setText(eredmeny);
                	kiszamolva = true;
                }
        	}
          } catch (ArithmeticException hiba) {
        		kiskijelzo.setText("Barom!");
        		kijelzo.setText("Nullaval nem osztunk!!");
        		idiot = true;
          }
            break;
        	
        case "+":
        	//0-val osztas utan torli a kepernyot
        	if (idiot) {
        		kiskijelzo.setText("");
        		kijelzo.setText("0");
        		idiot = false;
        	//A lenti "kijelzo"-n levo erteket es ezt a muveleti jelet felteszi a "kiskijelzo"-re	
        	} else if (kiskijelzo.getText().isEmpty() || kiszamolva) {
        		kiskijelzo.setText(kijelzo.getText() + " " + gombnyomas.toString() + " ");
        		kijelzo.setText("0");
        		kiszamolva = false;
        	//Muveleti jel csereje a felso "kiskijelzo"-n
        	} else if (!kiskijelzo.getText().isEmpty()) {
        		input = kiskijelzo.getText().substring(0, kiskijelzo.getText().length() - 3);
        		kiskijelzo.setText(input + " " + gombnyomas.toString() + " ");
        	}
        break;
        
        case "-":
        	//0-val osztas utan torli a kepernyot
        	if (idiot) {
        		kiskijelzo.setText("");
        		kijelzo.setText("0");
        		idiot = false;
        	//A lenti "kijelzo"-n levo erteket es ezt a muveleti jelet felteszi a "kiskijelzo"-re
        	} else if (kiskijelzo.getText().isEmpty() || kiszamolva) {
        		kiskijelzo.setText(kijelzo.getText() + " " + gombnyomas.toString() + " ");
        		kijelzo.setText("0");
        		kiszamolva = false;
        	//Muveleti jel csereje a felso "kiskijelzo"-n
        	} else if (!kiskijelzo.getText().isEmpty()) {
        		input = kiskijelzo.getText().substring(0, kiskijelzo.getText().length() - 3);
        		kiskijelzo.setText(input + " " + gombnyomas.toString() + " ");
        	}
        break;
        
        case "*":
        	//0-val osztas utan torli a kepernyot
        	if (idiot) {
        		kiskijelzo.setText("");
        		kijelzo.setText("0");
        		idiot = false;
        	//A lenti "kijelzo"-n levo erteket es ezt a muveleti jelet felteszi a "kiskijelzo"-re
        	} else if (kiskijelzo.getText().isEmpty() || kiszamolva) {
        		kiskijelzo.setText(kijelzo.getText() + " " + gombnyomas.toString() + " ");
        		kijelzo.setText("0");
        		kiszamolva = false;
        	//Muveleti jel csereje a felso "kiskijelzo"-n
        	} else if (!kiskijelzo.getText().isEmpty()) {
        		input = kiskijelzo.getText().substring(0, kiskijelzo.getText().length() - 3);
        		kiskijelzo.setText(input + " " + gombnyomas.toString() + " ");
        	}
        break;
        
        case "/":
        	//0-val osztas utan torli a kepernyot
        	if (idiot) {
        		kiskijelzo.setText("");
        		kijelzo.setText("0");
        		idiot = false;
        	//A lenti "kijelzo"-n levo erteket es ezt a muveleti jelet felteszi a "kiskijelzo"-re	
        	} else if (kiskijelzo.getText().isEmpty() || kiszamolva) {
        		kiskijelzo.setText(kijelzo.getText() + " " + gombnyomas.toString() + " ");
        		kijelzo.setText("0");
        		kiszamolva = false;
        	//Muveleti jel csereje a felso "kiskijelzo"-n
        	} else if (!kiskijelzo.getText().isEmpty()) {
        		input = kiskijelzo.getText().substring(0, kiskijelzo.getText().length() - 3);
        		kiskijelzo.setText(input + " " + gombnyomas.toString() + " ");
        	}
        break;
        
        case "0":
        case "1":
        case "2":
        case "3":
        case "4":
        case "5":
        case "6":
        case "7":
        case "8":
        case "9":
        	//0-val osztas utan az adott erteket beirja a "kijelzo"-re
        	if (idiot) {
        		kiskijelzo.setText("");
        		kijelzo.setText(gombnyomas.toString());
        		idiot = false;
        	//"=" utan az adott erteket beirja a "kijelzo"-re
        	} else if (kiszamolva) {
        		kiskijelzo.setText("");
        		kijelzo.setText(gombnyomas.toString());
        		kiszamolva = false;
        	//Szamok beirasa a "kijelzo"-re egymas utan	
        	} else if (!kijelzo.getText().equals("0")) {
        		int ertek = Integer.parseInt(kijelzo.getText());
        		int gombErtek = Integer.parseInt(gombnyomas.toString());
        		input = Integer.toString(ertek * 10 + gombErtek);
        		kijelzo.setText(input);
        	//Szam beirasa a "kijelzo"-re	
        	} else {
        		kijelzo.setText(gombnyomas.toString());
        	}
        break;
		}	
	}
}
