import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

public class Padeltesti extends JPanel {

	//Taustakuvan asetus
	private BufferedImage taustakuva;
	
	public Padeltesti() { 
		setBackground(Color.WHITE); 
		
		try {
			taustakuva = ImageIO.read(new File("C:\\Users\\piia.tapio\\eclipse-workspace\\Testi\\josh-calabrese-zcYRw547Dps-unsplash.jpg"));
			} 
		catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) { 
		super.paintComponent(g); 
		if (taustakuva != null) { 
			g.drawImage(taustakuva, 0, 0, this.getWidth(), this.getHeight(), this); 
			} 
		}
	
	//Ikkunan luominen
    public static void main(String[] args) {
        
    	JFrame ikkuna = new JFrame();
        ikkuna.setTitle("Padel-tietovisa");
        ikkuna.setSize(new Dimension(900, 500));
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Padeltesti ohjelma = new Padeltesti();
        ikkuna.getContentPane().add(ohjelma);
        ikkuna.setLocationRelativeTo(null);
        
        createMenuBar(ikkuna);
        ikkuna.setVisible(true);
    	}
        //Tehdään valikko
    private static void createMenuBar(JFrame frame) { 
    	var menuBar = new JMenuBar(); 
    	
    	var fileMenu = new JMenu("File"); 
    	
    	var newMenuItem = new JMenuItem("New"); 
    	var scoreMenuItem = new JMenuItem("Scores"); 
    	var exitMenuItem = new JMenuItem("Exit"); 
    	
    	newMenuItem.setToolTipText("Aloita uusi Padel-tietovisa"); 
    	newMenuItem.addActionListener((event) -> kaynnistaPadeltesti(frame)); 
    	
    	scoreMenuItem.setToolTipText("Näytä tulokset"); 
    	scoreMenuItem.addActionListener((event) -> näytäTulokset(frame));
    	
    	exitMenuItem.setToolTipText("Sulje ohjelma"); 
    	exitMenuItem.addActionListener((event) -> System.exit(0)); 
    	
    	fileMenu.add(newMenuItem); 
    	fileMenu.add(scoreMenuItem); 
    	fileMenu.add(exitMenuItem); 
    	
    	menuBar.add(fileMenu); 
    	
    	var helpMenu = new JMenu("Help"); 
    	var manualMenuItem = new JMenuItem("Instructions");
    	var aboutMenuItem = new JMenuItem("About"); 
    	
    	manualMenuItem.addActionListener((event) -> { JOptionPane.showMessageDialog(frame, "Aloitus\nAloita uusi padel-tietovisa valitsemalla File ja New.\n\nTietovisan lopettaminen\nVoit lopettaa tietovisan valitsemalla CANCEL.\n\nSulkeminen\nSaat suljettua ohjelman valitsemalla File ja Exit tai valitsemalla x ruudun oikeasta yläkulmasta.", "Tietoja", JOptionPane.INFORMATION_MESSAGE); });
    	aboutMenuItem.addActionListener((event) -> { JOptionPane.showMessageDialog(frame, "Ohjelman versio 1.0" + "\nTekijä Piia Tapio" + "\nLisätietoja piia.tapio@edu.keuda.fi", "Tietoja", JOptionPane.INFORMATION_MESSAGE); }); 
    	
    	helpMenu.add(manualMenuItem);
    	helpMenu.add(aboutMenuItem);
    	
    	menuBar.add(helpMenu); 
    	
    	frame.setJMenuBar(menuBar); 
    	}    
       
    private static void kaynnistaPadeltesti(JFrame frame) {
    	
        //Ohjelman aloitus
        JOptionPane.showMessageDialog(null, "Tervetuloa!");
        
        //Käyttäjän nimen kysyminen
        String nimi = JOptionPane.showInputDialog(null,"Mikä nimesi on?");
        if (nimi == null) { 
        	//JOptionPane.showMessageDialog(null, "Painoit CANCEL. Lopetan ohjelman."); 
        	return;}
        
        int valinta = JOptionPane.showConfirmDialog(null, "Pelaatko padelia " + nimi + "?");
        	 
        	if (valinta == JOptionPane.YES_OPTION) { 
            	JOptionPane.showMessageDialog(null, "Mahtavaa!"); 
                } 
            else if (valinta == JOptionPane.NO_OPTION) { 
                JOptionPane.showMessageDialog(null, "Kannattaa kokeilla!"); 
                } 
            else if (valinta == JOptionPane.CANCEL_OPTION) { 
                //JOptionPane.showMessageDialog(null, "Painoit CANCEL. Lopetan ohjelman.");
                return;
                }
        
        //Tarkistetaan halukkuus testiin
        int aloitus = JOptionPane.showConfirmDialog(null, "Haluatko kokeilla, paljonko saat oikein padel-tietovisaa, " + nimi + "?");
            
        	if (aloitus == JOptionPane.YES_OPTION) { 
                JOptionPane.showMessageDialog(null, "Mahtavaa, mennään siis asiaan!\nKysyn 10 kysymystä. Saat jokaisesta oikeasta vastauksesta yhden pisteen."
                    + "\nKatsotaan montako saat oikein."); 
            
            ArrayList<String> kysymykset = new ArrayList<>();
            HashMap<String, Integer> oikeavastaus = new HashMap<>();
            
            kysymykset.add("Saako pallon palauttaa lasiseinän kautta vastapuolelle?"); //1
            oikeavastaus.put("Saako pallon palauttaa lasiseinän kautta vastapuolelle?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Saako pallon palauttaa metalliverkon kautta vastapuolelle?"); //2
            oikeavastaus.put("Saako pallon palauttaa metalliverkon kautta vastapuolelle?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Onko totta, että pallon tulee aina pompata valkoisten viivojen sisällä?"); //3
            oikeavastaus.put("Onko totta, että pallon tulee aina pompata valkoisten viivojen sisällä?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Saako nelinpelissä molemmat pelaajat lyödä palloa vuoron perään ennen palautusta vastapuolelle?"); //4
            oikeavastaus.put("Saako nelinpelissä molemmat pelaajat lyödä palloa vuoron perään ennen palautusta vastapuolelle?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Saako pallo osua uudelleen samalla puolella kenttään, jos se osuu välissä seinään?"); //5
            oikeavastaus.put("Saako pallo osua uudelleen samalla puolella kenttään, jos se osuu välissä seinään?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Syöttöä vastaanottaessa palloa ei saa lyödä suoraan ilmasta. Onko tämä totta?"); //6
            oikeavastaus.put("Syöttöä vastaanottaessa palloa ei saa lyödä suoraan ilmasta. Onko tämä totta?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Ensimmäinen syöttö syöttövuorolla lähtee aina oikeasta ruudusta. Onko tämä totta?"); //7
            oikeavastaus.put("Ensimmäinen syöttö syöttövuorolla lähtee aina oikeasta ruudusta. Onko tämä totta?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Padelmailamallit ovat timantti, pyöreä ja pisara. Onko tämä totta?"); //8
            oikeavastaus.put("Padelmailamallit ovat timantti, pyöreä ja pisara. Onko tämä totta?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Saako padelmailaan lisätä painoja?"); //9
            oikeavastaus.put("Saako padelmailaan lisätä painoja?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Padelmaila painaa keskimäärin puoli kiloa. Onko tämä totta?"); //10
            oikeavastaus.put("Padelmaila painaa keskimäärin puoli kiloa. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Virallisten sääntöjen mukaan naisten täytyy käyttää lyhyttä hametta, hameshortseja tai mekkoa. Onko tämä totta?"); //11
            oikeavastaus.put("Virallisten sääntöjen mukaan naisten täytyy käyttää lyhyttä hametta, hameshortseja tai mekkoa. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Saako syöttö lähteä padelissa ala- tai yläkautta?"); //12 
            oikeavastaus.put("Saako syöttö lähteä padelissa ala- tai yläkautta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Saako padelissa lyödä palloa kahden käden otteella, kuten tenniksessä?"); //13 
            oikeavastaus.put("Saako padelissa lyödä palloa kahden käden otteella, kuten tenniksessä?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Saako pallon lyödä suoraan vastustajan kenttäpuoliskon seinään?"); //14 
            oikeavastaus.put("Saako pallon lyödä suoraan vastustajan kenttäpuoliskon seinään?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Onko padel-kentän kaikki seinät lasia?"); //15 
            oikeavastaus.put("Onko padel-kentän kaikki seinät lasia?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Käytetäänkö padelissa ja tenniksessä samoja palloja?"); //16 
            oikeavastaus.put("Käytetäänkö padelissa ja tenniksessä samoja palloja?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Syötössä syöttäjä ei saa astua syöttöruudun viivan yli ennen kuin pallo osuu maahan. Onko tämä totta?"); //17 
            oikeavastaus.put("Syötössä syöttäjä ei saa astua syöttöruudun viivan yli ennen kuin pallo osuu maahan. Onko tämä totta?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Syöttövuoro vaihtuu aina yhden syötön jälkeen vastustajalle. Onko tämä totta?"); //18 
            oikeavastaus.put("Syöttövuoro vaihtuu aina yhden syötön jälkeen vastustajalle. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Voiko syöttö osua ensin omaan kenttäpuoliskoon ja sitten vastustajan kenttäpuoliskoon?"); //19 
            oikeavastaus.put("Voiko syöttö osua ensin omaan kenttäpuoliskoon ja sitten vastustajan kenttäpuoliskoon?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Saako syöttäjä yrittää syöttöä uudelleen, jos ensimmäinen syöttö epäonnistuu?"); //20 
            oikeavastaus.put("Saako syöttäjä yrittää syöttöä uudelleen, jos ensimmäinen syöttö epäonnistuu?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Onko syöttö hyväksytty, jos pallo osuu raja-alueen viivalle?"); //21 
            oikeavastaus.put("Onko syöttö hyväksytty, jos pallo osuu raja-alueen viivalle?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Syöttäjän täytyy seistä ruudussa valkoisten viivojen sisällä syötössä. Onko tämä totta?"); //22 
            oikeavastaus.put("Syöttäjän täytyy seistä ruudussa valkoisten viivojen sisällä syötössä. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Saako syöttö osua vastustajan kenttäpuoliskon lasiseinään ilman, että se ensin osuu kenttään?"); //23 
            oikeavastaus.put("Saako syöttö osua vastustajan kenttäpuoliskon lasiseinään ilman, että se ensin osuu kenttään?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Syötössä pallo heitetään ilmaan ja lyödään vastustajalle suoraan ilmasta. Onko tämä totta?"); //24 
            oikeavastaus.put("Syötössä pallo heitetään ilmaan ja lyödään vastustajalle suoraan ilmasta. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Noudatetaanko padelissa samaa pistelaskusääntöä kuin tenniksessä eli 15-30-40-peli?"); //25 
            oikeavastaus.put("Noudatetaanko padelissa samaa pistelaskusääntöä kuin tenniksessä eli 15-30-40-peli?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Pallon osuminen metalliverkkoon on aina virhe. Onko tämä totta?"); //26 
            oikeavastaus.put("Pallon osuminen metalliverkkoon on aina virhe. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Syöttötilanteessa kaikkien pelaajien pitää olla valkoisten viivojen takana. Onko tämä totta?"); //27 
            oikeavastaus.put("Syöttötilanteessa kaikkien pelaajien pitää olla valkoisten viivojen takana. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Vastustaja saa pisteen, jos palautuksessa pallo osuu keskiverkkoon, vaikka jatkaisikin matkaa vastustajan puolelle kenttään osuen. Onko tämä totta?"); //28 
            oikeavastaus.put("Vastustaja saa pisteen, jos palautuksessa pallo osuu keskiverkkoon, vaikka jatkaisikin matkaa vastustajan puolelle kenttään osuen. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Kuten tenniksessä, padelissa käytetään sisempiä viivoja kaksinpelissä ja ulompia nelinpelissä. Onko tämä totta?"); //29
            oikeavastaus.put("Kuten tenniksessä, padelissa käytetään sisempiä viivoja kaksinpelissä ja ulompia nelinpelissä. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Padel-ottelu voidaan käydä vain nelinpelinä (2 + 2). Onko tämä totta?"); //30 
            oikeavastaus.put("Padel-ottelu voidaan käydä vain nelinpelinä (2 + 2). Onko tämä totta?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Saako syöttäjä hypätä syöttäessään, jotta saa enemmän voimaa syöttöön?"); //31 
            oikeavastaus.put("Saako syöttäjä hypätä syöttäessään, jotta saa enemmän voimaa syöttöön?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Saako syöttöön ottaa vauhtia kävellen tai juosten?"); //32 
            oikeavastaus.put("Saako syöttöön ottaa vauhtia kävellen tai juosten?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Syötössä pallon tulee pompata ulos vastustajan ruudusta. Onko tämä totta?"); //33 
            oikeavastaus.put("Syötössä pallon tulee pompata ulos vastustajan ruudusta. Onko tämä totta?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Pallon saa palauttaa yhden tai useamman lasin kautta. Onko tämä totta?"); //34 
            oikeavastaus.put("Pallon saa palauttaa yhden tai useamman lasin kautta. Onko tämä totta?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Pelataanko padelissa erä kuuteen pisteeseen tai 6-6 -tilanteessa pelaamalla tie-break?"); //35
            oikeavastaus.put("Pelataanko padelissa erä kuuteen pisteeseen tai 6-6 -tilanteessa pelaamalla tie-break?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Voiko padelia pelata tenniskentällä?"); //36 
            oikeavastaus.put("Voiko padelia pelata tenniskentällä?", JOptionPane.NO_OPTION);
            
            kysymykset.add("Ovatko padelin ja tenniksen kentät eri kokoiset?"); //37 
            oikeavastaus.put("Ovatko padelin ja tenniksen kentät eri kokoiset?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Onko totta, että padelissa yhdistyy tennis ja squash?"); //38
            oikeavastaus.put("Onko totta, että padelissa yhdistyy tennis ja squash?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Onko padelissa keskiverkko korkeampi kuin tenniksessä?"); //39
            oikeavastaus.put("Onko padelissa keskiverkko korkeampi kuin tenniksessä?", JOptionPane.YES_OPTION);
            
            kysymykset.add("Pelataanko padelia ja tennistä samoilla mailoilla?"); //40    
            oikeavastaus.put("Pelataanko padelia ja tennistä samoilla mailoilla?", JOptionPane.NO_OPTION);
         
            //Kysymysten arvonta   
            Collections.shuffle(kysymykset); 
            ArrayList<String> valitutkysymykset = new ArrayList<>(kysymykset.subList(0, 10));   
            
            //Alustetaan pisteidenlasku           
	        int tulos = 0;
            
	        // Kysymysten valinta ja vastausten tarkistus 
            for (String kysymys : valitutkysymykset) { 
            	int vastaus = JOptionPane.showConfirmDialog(null, "<html><b>" + kysymys + "</b></html>", "Padel-kysymys", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            		if (vastaus == oikeavastaus.get(kysymys)) {
            			//JOptionPane.showMessageDialog(null, "Vastasit oikein!", "Oikein", JOptionPane.INFORMATION_MESSAGE);
            			tulos++;
            		}
            		else if (vastaus == JOptionPane.NO_OPTION || vastaus == JOptionPane.YES_OPTION) {
            			//JOptionPane.showMessageDialog(null,  "Vastasit väärin!", "Väärin", JOptionPane.ERROR_MESSAGE);
            		}
            		else if (vastaus == JOptionPane.CANCEL_OPTION) {
            			JOptionPane.showMessageDialog(null, "Lopetit testin." + "\nSait tähän mennessä " + tulos + " pistettä.", "Lopetetaan", JOptionPane.WARNING_MESSAGE);
            			return;
            		}
        		}
            
            
	        //Loppupisteiden julistaminen ja tallennus
	        JOptionPane.showMessageDialog(null, "Sait, " + nimi + ", yhteensä " + tulos + "/10 pistettä!");
	        
	        List<String[]> tulokset = new ArrayList<>(); 
	    	//tulokset.add(new String[]{"Nimi", "Tulos"}); 
	    	tulokset.add(new String[]{nimi, String.valueOf(tulos)}); 
	    	kirjoitaCSV("padeltestitulokset.csv", tulokset);
        	}
        	
    	
	    else if (aloitus == JOptionPane.NO_OPTION) { 
	        JOptionPane.showMessageDialog(null, "Joku toinen kerta sitten."); 
	        return;
	        } 
	    else if (aloitus == JOptionPane.CANCEL_OPTION) { 
	        //JOptionPane.showMessageDialog(null, "Lopetan ohjelman.");
	        return;
	        }
    
	 } // Metodi tulosten kirjoittamiseen CSV-tiedostoon 
		
    public static void kirjoitaCSV(String tiedostonNimi, List<String[]> data) { 
			try (FileWriter writer = new FileWriter(tiedostonNimi, true)) {  
				for (String[] rivi : data) { 
					writer.append(String.join(",", rivi)); 
					writer.append("\n"); } 
				} 
			catch (IOException e) { 
				e.printStackTrace(); 
				}		
		}
    private static void näytäTulokset(JFrame frame) { 
    	try { 
    		BufferedReader reader = new BufferedReader(new FileReader("padeltestitulokset.csv")); 
    		String line; 
    		Vector<String> columnNames = new Vector<>(); 
    		Vector<Vector<Object>> data = new Vector<>();  
    		if ((line = reader.readLine()) != null) { 
    			String[] columns = line.split(","); 
    			for (String column : columns) { 
    				columnNames.add(column); 
    				} 
    			}  
    		
    		while ((line = reader.readLine()) != null) { 
    			String[] row = line.split(","); 
    			Vector<Object> dataRow = new Vector<>(); 
    			Collections.addAll(dataRow, row); 
    			data.add(dataRow); 
    			} 
			reader.close();  
			
			data.sort((a, b) -> Integer.compare(Integer.parseInt((String) b.get(1)), Integer.parseInt((String) a.get(1))));
			
			JTable table = new JTable(new DefaultTableModel(data, columnNames)); 
			JScrollPane scrollPane = new JScrollPane(table); 
			JOptionPane.showMessageDialog(frame, scrollPane, "Padel-tietovisan tulokset", JOptionPane.PLAIN_MESSAGE); 
			} 
    	
    	catch (IOException e) { 
    		e.printStackTrace(); 
    		} 
    	}
    
    	
    
    
}

