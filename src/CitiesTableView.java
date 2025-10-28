
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CitiesTableView extends Stage {
	BorderPane bp = new BorderPane();
	TableView<Location> tableView = new TableView<Location>();
	Button addBT = new Button("add");
	Button deleteBT = new Button("delete");
	Button searchBT = new Button("search");
	TextField locationTF = new TextField("location");
	HBox hBox = new HBox(10, addBT, deleteBT);
	TextField searchTF = new TextField("location");
	TextField searchMartyerTF = new TextField("Martyer");
	Button searchMartyerBT = new Button("search");
	HBox searchHB = new HBox(10, searchTF, searchBT, searchMartyerTF, searchMartyerBT);
	VBox vBox = new VBox(10, locationTF, hBox);

	public CitiesTableView() {
		tableView.setEditable(true);
		TableColumn<Location, String> locationCol = new TableColumn<>("Location");
		locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
		locationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		locationCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Location, String>>() {
			@Override
			public void handle(CellEditEvent<Location, String> e) {
				Location location = e.getRowValue();
				// update location data ...
				location.setLocation(e.getNewValue());
				tableView.getItems().remove(location);
				StartClass.linked.sortList();
				int index = StartClass.linked.search(location);
				tableView.getItems().add(index, location);

			}
		});
		TableColumn<Location, String> infoCol = new TableColumn<>("info");
		infoCol.setCellValueFactory(new PropertyValueFactory<>("InfoBT"));

		TableColumn<Location, String> summeryCol = new TableColumn<>("summery");
		summeryCol.setCellValueFactory(new PropertyValueFactory<>("SummeryBT"));

		searchBT.setOnAction(e -> {
			if (searchTF.getText().isEmpty()) {
				this.hide();
				new CitiesTableView();
			}
			for (int i = 0; i < StartClass.linked.getCount(); i++) {
				Location location = StartClass.linked.get(i);
				if (location.getLocation().contains(searchTF.getText()) == false) {
					// remove all data that does not have the searched name on it .
					tableView.getItems().removeAll(StartClass.linked.get(i));
				}
			}
		});
		searchMartyerBT.setOnAction(e -> {
			boolean flag = false;
			for (int i = 0; i < StartClass.linked.getCount(); i++) {
				Location location = StartClass.linked.get(i);

				if (location.getaVlTree1().find(searchMartyerTF.getText()) != null) {

					Martyer martyer = location.getaVlTree1().find(searchMartyerTF.getText()).element;
					Label label = new Label(location.getLocation() + martyer.toString());
//					Scene scene = new Scene(label, 450, 450);
//					this.setScene(scene);
					label.setFont(new Font(15));
					vBox.getChildren().add(label);
					flag = true;
				}
			}
			if (flag == false) {
				Label label = new Label("cannot find martyer.");
				label.setFont(new Font(15));
//				Scene scene = new Scene(label, 450, 450);
//				this.setScene(scene);
				vBox.getChildren().add(label);

			}
		});

		for (int i = 0; i < StartClass.linked.getCount(); i++) {
			tableView.getItems().add(StartClass.linked.get(i));
		}

		deleteBT.setOnAction(e -> {
			// tableView.getSelectionModel().getSelectedItem() return object location.
			StartClass.linked.remove(tableView.getSelectionModel().getSelectedItem());// remove it from saved data
			tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());// remove it from table view
		});

		addBT.setOnAction(e -> {
			Location location = new Location(locationTF.getText());
			if (StartClass.linked.search(location.getLocation()) == -1) {
				StartClass.linked.addLast(location);
				StartClass.linked.sortList();
				// after using sort the index of it will change
				int index = StartClass.linked.search(location.getLocation());
				// to know where to put it in the table
				tableView.getItems().add(index, location);
				locationTF.setText("location");
			} else {
				System.out.println("location already exist");
			}
			StartClass.linked.sortList();
		});

		bp.setTop(searchHB);
		bp.setAlignment(searchTF, Pos.CENTER);
		tableView.getColumns().add(locationCol);
		tableView.getColumns().add(infoCol);
		tableView.getColumns().add(summeryCol);
		bp.setCenter(tableView);
		vBox.setAlignment(Pos.CENTER);
		bp.setAlignment(vBox, Pos.CENTER);

		bp.setBottom(vBox);
		Scene scene = new Scene(bp, 450, 450);
		this.setScene(scene);
		this.show();
	}

}