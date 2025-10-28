
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartClass extends Application {
	Button startBT = new Button("start");
	Button loadBT = new Button("load");
	Button saveBT = new Button("save");
	static DoubleLinkedList<Location> linked = new DoubleLinkedList<>();
	HBox hBox = new HBox(10, startBT, loadBT, saveBT);
	QueueList queueList = new QueueList();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		BorderPane bP = new BorderPane();
		Label mainL = new Label("\t\t\tWelcome to " + "\n" + "Martyers honor list: Comp 242 Project 2");
		mainL.setFont(new Font("Verdana", 15));
		bP.setAlignment(mainL, Pos.CENTER);
		bP.setTop(mainL);
		ImageView imageView = new ImageView("C:\\Users\\ahmad\\eclipse-workspace\\structPhase1\\data.jpg");
		imageView.setFitWidth(250);
		imageView.setFitHeight(250);

		loadBT.setOnAction(e -> {

			FileChooser fileChooser = new FileChooser();
			File load = fileChooser.showOpenDialog(null);
			load = new File(load.getAbsolutePath());
			readFromFile(load);

		});

		imageView.setRotate(35);
		hBox.setAlignment(Pos.CENTER);
		bP.setCenter(imageView);
		bP.setBottom(hBox);
		startBT.setStyle(" -fx-background-color: white;-fx-font-size: 12px;" + "    -fx-font-weight: bold;"
				+ "    -fx-text-fill: #333333;"
				+ "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		loadBT.setStyle(" -fx-background-color: white;-fx-font-size: 12px;" + "    -fx-font-weight: bold;"
				+ "    -fx-text-fill: #333333;"
				+ "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");

		saveBT.setStyle(" -fx-background-color: white;-fx-font-size: 12px;" + "    -fx-font-weight: bold;"
				+ "    -fx-text-fill: #333333;"
				+ "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		startBT.setOnAction(e -> {
			// sort the data before showing it ...
			linked.sortList();
			new CitiesTableView();
		});
		saveBT.setOnAction(e -> {

			FileChooser fileChooser = new FileChooser();
			File save = fileChooser.showOpenDialog(null);
			save = new File(save.getAbsolutePath());
			writeInFile(save);
		});

		Scene scene = new Scene(bP, 450, 450);
		bP.setStyle("-fx-background-color: gray;");
		stage.setTitle("honor list");
		stage.setScene(scene);
		stage.show();

	}

	public void readFromFile(File file) {
	    try {
	        FileReader reader = new FileReader(file);
	        BufferedReader br = new BufferedReader(reader);
	        String line = br.readLine(); // skip header row
	        String name, location;
	        int age;
	        char gender;
	        Date dateOfDeath;

	        LinkedList<Martyer> list = new LinkedList<>();

	        while ((line = br.readLine()) != null) {
	            String[] tkz = line.split(",");

	            name = tkz[0].trim();
	            age = tkz[1].isEmpty() ? 0 : Integer.parseInt(tkz[1].trim());
	            location = tkz[2].trim();
	            dateOfDeath = new SimpleDateFormat("dd/MM/yyyy").parse(tkz[3].trim());
	            gender = tkz[4].trim().charAt(0);

	            if (StartClass.linked.search(location) == -1) {
	                Location loc = new Location(location, new Martyer(name, age, dateOfDeath, gender));
	                StartClass.linked.addFisrt(loc);
	                System.out.println("Added new location: " + location + " with Martyer: " + name);
	            } else {
	                Location loc = StartClass.linked.get(StartClass.linked.search(location));
	                loc.setMartyer(new Martyer(name, age, dateOfDeath, gender));
	                System.out.println("Added Martyer: " + name + " to existing location: " + location);
	            }
	        }

	        br.close();

	        // Print AVL Trees after loading all data
	        System.out.println("\n=== AVL Tree By Name ===");
	        for (int i = 0; i < StartClass.linked.getCount(); i++) {
	            Location loc = StartClass.linked.get(i);
	            System.out.println("Location: " + loc.getLocation());
	            loc.getaVlTree1().inOrder(); // assumes inOrder prints martyers
	        }

	        System.out.println("\n=== AVL Tree By Date ===");
	        for (int i = 0; i < StartClass.linked.getCount(); i++) {
	            Location loc = StartClass.linked.get(i);
	            System.out.println("Location: " + loc.getLocation());
	            loc.getAvlTree2().inOrder(); // assumes inOrder prints DateStack contents
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}


	public void writeInFile(File file) {
		try {
			AVlTree1<Martyer> aVlTree1;
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter Bw = new BufferedWriter(fWriter);
			Bw.write("Name,");
			Bw.write("age,");
			Bw.write("location,");
			Bw.write("date of death,");
			Bw.write("gender");
			Bw.newLine();
			for (int i = 0; i < linked.getCount(); i++) {
				aVlTree1 = linked.get(i).getaVlTree1();
				queueList = new QueueList();
				InOrder(aVlTree1.root);
				while (!queueList.isEmpty()) {
					Martyer martyer = queueList.dequeue();
					Bw.write(martyer.getName() + ",");
					Bw.write(martyer.getAge() + ",");
					Bw.write(linked.get(i).getLocation() + ",");
					Bw.write(martyer.getDateOfDeath() + ",");
					Bw.write(martyer.getGender() + ",");
					Bw.newLine();

				}

			}
			Bw.close();
			fWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void InOrder(AvlNode1<Martyer> root) {

		if (root != null) {
			InOrder(root.left);
			queueList.enqueue(root.element);
			InOrder(root.right);
		}

	}

}
