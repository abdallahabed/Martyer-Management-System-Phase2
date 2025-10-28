
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class MartyesInfo extends Stage {
	QueueList queueList = new QueueList();
	BorderPane bPane = new BorderPane();
	Button addBT = new Button("add");
	Button deleteBT = new Button("delete");
	Button nextBT = new Button("Next");
	Button privousBT = new Button("previos");
	TextField nameTF = new TextField("Name");
	TextField ageTF = new TextField("age");
	TextField dateOfDeathTF = new TextField("DD/MM/YY");
	TextField genderTF = new TextField("gender");
	HBox textFiledHB = new HBox(10, nameTF, ageTF, dateOfDeathTF, genderTF);
	HBox buttonsHB = new HBox(10, addBT, deleteBT);
	VBox vBox = new VBox(10, textFiledHB, buttonsHB);

	Button searchBT = new Button("search");
	TextField searchTF = new TextField();
	HBox searchHB = new HBox(10, searchTF, searchBT);
	TableView<Martyer> tableView = new TableView<>();

	public MartyesInfo(Location l) {

		l.getaVlTree1();
		Label locationL = new Label(l.getLocation());
		VBox topVBox = new VBox(10, locationL, searchHB);
		bPane.setTop(topVBox);
		searchHB.setAlignment(Pos.CENTER);
		topVBox.setAlignment(Pos.CENTER);
		locationL.setFont(new Font("Verdana", 15));

		tableView.setEditable(true);
		TableColumn<Martyer, String> nameCol = new TableColumn<>("name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Martyer, String>>() {

			@Override
			public void handle(CellEditEvent<Martyer, String> e) {
				Martyer martyer = e.getRowValue();
				martyer.setName(e.getNewValue());
			}
		});

		TableColumn<Martyer, Integer> ageCol = new TableColumn<>("age");
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		ageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		ageCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Martyer, Integer>>() {

			@Override
			public void handle(CellEditEvent<Martyer, Integer> e) {
				Martyer martyer = e.getRowValue();
				martyer.setAge(e.getNewValue());
			}

		});
		TableColumn<Martyer, Date> dataOfDeathCol = new TableColumn<>("date of death");
		dataOfDeathCol.setCellValueFactory(new PropertyValueFactory<>("DateOfDeath"));
		dataOfDeathCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
		dataOfDeathCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Martyer, Date>>() {

			@Override
			public void handle(CellEditEvent<Martyer, Date> e) {
				Martyer martyer = e.getRowValue();
				martyer.setDateOfDeath(e.getNewValue());
				// remove it from table view
				tableView.getItems().remove(martyer);
//				int index = l.getList().search(martyer);
				tableView.getItems().add(martyer);
			}
		});
		TableColumn<Martyer, Character> genderCol = new TableColumn<>("gender");
		genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
		genderCol.setCellFactory(TextFieldTableCell.forTableColumn(new CharacterStringConverter()));
		genderCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Martyer, Character>>() {

			@Override
			public void handle(CellEditEvent<Martyer, Character> e) {
				Martyer martyer = e.getRowValue();
				martyer.setGender(e.getNewValue());
			}
		});
		tableView.getColumns().addAll(nameCol, ageCol, dataOfDeathCol, genderCol);
		bPane.setLeft(privousBT);
		bPane.setAlignment(privousBT, Pos.CENTER);
		bPane.setRight(nextBT);
		bPane.setAlignment(nextBT, Pos.CENTER);

		deleteBT.setOnAction(e -> {
			// tableView.getSelectionModel().getSelectedItem() return object location.
			l.getaVlTree1().delete(tableView.getSelectionModel().getSelectedItem());// remove it from saved data
			tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());// remove it from table view

		});

		addBT.setOnAction(e -> {

			try {
				String name = nameTF.getText();
				int age = Integer.parseInt(ageTF.getText());
				Date dateOfDeath = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfDeathTF.getText());
				char gender = genderTF.getText().charAt(0);
				Martyer martyer = new Martyer(name, age, dateOfDeath, gender);
				if (l.getaVlTree1().find(name) == null) {
					l.getaVlTree1().insert(martyer);
					tableView.getItems().add(martyer);
				} else {
					vBox.getChildren().add(new Label(name + " already exist"));

				}
				if (l.getAvlTree2().find(dateOfDeath) != null) {
					l.getAvlTree2().find(dateOfDeath).element.getStackList().push(martyer);
				} else {
					l.getAvlTree2().insert(new DateStack(dateOfDeath, martyer));
				}

				ageTF.setText("");
				nameTF.setText("");
				dateOfDeathTF.setText("");
				genderTF.setText("");

			} catch (ParseException e1) {
				e1.printStackTrace();
			}

		});
		nextBT.setOnAction(e -> {
			Location location;
			// that mean you reached the end so return to the first one.
			if (StartClass.linked.search(l) == StartClass.linked.getCount() - 1)
				location = StartClass.linked.get(0);
			else
				location = StartClass.linked.get(StartClass.linked.search(l) + 1);

			this.hide();
			new MartyesInfo(location);
		});
		privousBT.setOnAction(e -> {
			Location location;
			// that mean you are at the biggening so return to the last one.
			if (StartClass.linked.search(l) == 0)
				location = StartClass.linked.get(StartClass.linked.getCount() - 1);
			else
				location = StartClass.linked.get(StartClass.linked.search(l) - 1);

			this.hide();
			new MartyesInfo(location);
		});

		searchBT.setOnAction(e -> {
			queueList = new QueueList();
			if (searchTF.getText().isEmpty()) {
				this.hide();
				new MartyesInfo(l);
			}
			InOrder(l.getaVlTree1().root);
			while (!queueList.isEmpty()) {
				Martyer martyer = queueList.dequeue();
				if (martyer.getName().contains(searchTF.getText()) == false) {
					tableView.getItems().remove(martyer);
				}
			}

		});
		queueList = new QueueList();
		InOrder(l.getaVlTree1().root);
		while (!queueList.isEmpty()) {
			Martyer martyer = queueList.dequeue();
			tableView.getItems().add(martyer);
			// Enqueue left child
		}

		bPane.setCenter(tableView);
		bPane.setBottom(vBox);
		Scene scene = new Scene(bPane, 650, 650);
		this.setScene(scene);
		this.show();

	}

	void InOrder(AvlNode1<Martyer> root) {

		if (root != null) {
			InOrder(root.left);
			queueList.enqueue(root.element);
			InOrder(root.right);
		}

	}

	public TableView<Martyer> getTableView() {
		return tableView;
	}

	public void setTableView(TableView<Martyer> tableView) {
		this.tableView = tableView;
	}

}
