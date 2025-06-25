# Overview
This project enhances the previous console‑based Inventory Management System by introducing a graphical user interface built with Java Swing and organised according to the Model–View–Controller (MVC) architecture pattern. It is the deliverable for **CS 349 – Java Programming with Applications (HW #3 - GUI & MVC) Section: 0001** in **Spring 2025 w/ Dr. Adu Baffour**, and is intended to reinforce junior‑level concepts of object‑oriented design, event handling, layered architecture, and GUI development—all while preserving the core inventory‑management logic.

The assignment scope is distilled into four collaborative layers:
1. Model layer – InventoryItem and InventoryModel encapsulate business rules and validation. Persistent storage is handled by FileBased/Repository, which silently load data from inventory.txt at startup (creating the file if it does not yet exist).

2. View layer – InventoryView (a JFrame) presents:
    - A table listing all inventory items,
    - An input form with their appropriate labels `(ID, name, quantity, price)`
    - Action buttons for `Add`, `Update`, `Delete`, `Clear`, `Back`, and `Submit` — all arranged with responsive Swing layouts and accompanied by success or error dialogs.

3. Controller layer – CtrlerInventory wires user interactions (button clicks, table selections) to the model and view, manages validation, and surfaces informative feedback dialogs.

4. Repository layer - Consists of two important files: Interface `Repository` and the class FileBased.
    - `Repository` permits the use of importing & exporting using the specific method calls of `loadInventory()` and `saveInventory()`.
    - `FileBased` implements `Repository`, permitting **text-based** reading & writing to and from the "inventory.txt" file (where file name declaration is done in `App.java`).

The application does NOT require the intitial creation of said file, but if the user holds a badly formatted file of `inventory.txt` then the program **will** delete the existing `inventory.txt` before going through process of inventory loading with a "clean slate".

---

While the deliverable remains OOP‑centric to meet course objectives, selective FP constructs—such as Java Streams, specialized method references, Supplier<T> factories, and Lambda Expressions—are used to eliminate boilerplate and keep pure operations isolated. These FP helpers live in clearly marked utility sections, making it easy to trace behaviour and debug without scanning entire classes.

Completing these layers demonstrates separation of concerns, proper MVC flow, data‑driven state management, consistent DRY Principles and Hollywood Principles, and robust user‑oriented error handling as required by the specification.

## Features

- **Two-stage CRUD workflow**: Users select Add, Update, or Delete in the main panel, then confirm or cancel via **Submit**, **Back**, and **Clear** buttons displayed after panel swap
- **Responsive Layout**: `JTable` for live item display (with dynamic text fields to enable/disable based on current operation)
- **Silent Persistence**: Automatic load from and save to `inventory.txt` at startup/shutdown
- **Validation & Feedback**: Input checks with `Supplier`-based validators and `JOptionPane` dialogs for error or success messages
- **Functional Helpers**: Java Streams and pure utility methods for concise business logic
- **Extra Features**: Data Export Functionality & Keyboard Shortcut/Hotkey (Enter = Submit button on Click)

# Project Layout
```
<root>
├── src/
│   ├── app/
│   │   └── App.java
│   ├── controller/
│   │   └── CtrlerInventory.java
│   ├── ioOperation/
│   │   ├── FileBased.java
│   │   └── Repository.java
│   ├── model/
│   │   ├── InventoryItem.java
│   │   └── InventoryModel.java
│   └── view/
│       └── InventoryView.java
├── inventory.txt   # optional persisted data file
└── README.md
```
# UI Layout

### Main Window
- **JLabel** for greeting user to window
- **JTable** listing all inventory item elements with labeled columns
- **Action Buttons**: Add, Update, Delete

### Add / Update / Delete
- Clicking **Add**, **Update**, or **Delete** on the main panel swaps in the edit panel with form fields and **Submit**, **Back**, **Clear** buttons at the bottom
- All JTextFields that are enabled/disabled are set to specific Foreground and Background colors (Black/White for enabled, or Gray/Light Gray for disabled) each
- **Submit** executes the selected operation:
    - Add immediately creates and inserts new item
    - Update/Delete first prompts user for ID paired with Item in question, before doing 1 of the following:
      - Update State 2 displays all existing information pulled from a valid ID into the appropriate text fields for the user to edit, where upon clicking Submit for the 2nd time will actively modify the existing tuple with either using the valid changed text field information, or if it fails then silently using the original information automatically.
      - Delete displays all existing information pulled from the valid ID into its appropriate fields, with editing **disabled** and the text **red** to alert the user of a critical change. If the user clicks Submit again, tuple is immediately removed. If the user clicks "Back", then all but ID are cleared with ID being revered to editable and in standard Black & White.
- **Back** returns to the previous panel without changing existing model data
- **Clear** resets all currently editable form fields to empty

### Error Handling
- Text Field Validation errors presented in `JOptionPane.showMessageDialog`
- Intentional no-state update in event of error after closing error window
- Graceful handling of file I/O exceptions

### Data Persistence Confirmation
- Silent load from `inventory.txt` at launch
- Automatic deletion of `inventory.txt` during load and recursive call in event of read error
- Automatic file save on exit or window close
- System Error status message displaying failed load/save state

# Screenshots

## Initial/Main Window  
![Main Window](https://github.com/user-attachments/assets/894a8c01-e5cf-42a2-bff9-acb76caeff57)

<details open>
<summary>CRUD Operations</summary>  
  <details>
    <summary>Add Operation</summary>
    
  Initial Add Window:  
  ![Add State](https://github.com/user-attachments/assets/2ab4a69c-8816-47ec-8afe-fbc21c2bc003)
    
  ### Successfull Add:  
  ![Confirmation Window](https://github.com/user-attachments/assets/63390308-dab9-467b-89cd-b1907a026da4)
  ![Post-Add Tabel](https://github.com/user-attachments/assets/94be883e-3d4e-442a-a125-a9c7f589c96d)
    
  Failed Addition (displaying 1 empty field for example):  
  ![Failure Window](https://github.com/user-attachments/assets/28d32998-518d-409d-8f1d-b2a32a807a7c)
  ![Reopens Edit Window](https://github.com/user-attachments/assets/9241778b-d4b0-4a7a-b60d-71cbbc13e665)
  
  </details>
  <details>
    <summary>Clear Operation</summary>
      
  Before:  
  ![Before Clear](https://github.com/user-attachments/assets/200f14f5-df4f-4b2a-be08-5d402caca58d)
      
  After:  
  ![After Clear](https://github.com/user-attachments/assets/115c11c1-9caf-4838-93a9-88b6c37029f5)
      
  Clearing when No Field is editable:  
  ![Post Clear](https://github.com/user-attachments/assets/d74e692b-ed50-4196-99e2-bf76986d17b6)
  </details>
  
  <details>
    <summary>Delete Operation</summary>
    
  Prompts for desired ID:  
  ![Delete Phase 1](https://github.com/user-attachments/assets/ddbf6a0b-70e5-4183-9b18-0813fe30058f)
    
  Bad ID Entered:  
  ![ID DNE](https://github.com/user-attachments/assets/662a3859-9623-4b60-942a-507a90be9608)
  ![Last Scene](https://github.com/user-attachments/assets/6d6d3f78-bf39-41bf-b72a-db3ad95a9938)
    
  ID Found:  
  ![ID Exists](https://github.com/user-attachments/assets/7382329b-628a-4ae5-86ec-c7b9418887d0)
    
  > **⚠️⚠️IMPORTANT⚠️⚠️**  
  > At this stage, you ***MUST*** click `Submit` again to confirm your decision to delete.  
  > If the ***Red*** information in the text fields are ***not correct***, click `Back` to revert to the previous scene

  
  ### Successful Delete:  
  ![Confirmation Window](https://github.com/user-attachments/assets/a74e3b8f-9fc3-4783-952c-1879fb9ebbcc)
  ![Post-Delete Table](https://github.com/user-attachments/assets/9b00b2f3-95a7-46ad-bbc4-304dc3141b77)
  </details>
  
  <details>
    <summary>Update Operation</summary>
    
  Prompts for desired ID (identical to Delete Operation prompt):  
  ![Update Phase 1](https://github.com/user-attachments/assets/2d516f57-d43f-47c6-8ee0-8f8479ddf069)
    
  Update ID Found:  
  ![Update Phase 2](https://github.com/user-attachments/assets/5921c9df-f4fe-4ceb-9b54-1ae3dd133bfc)
    
  ### Successful Update w/ All Changed Fields:  
  ![Confirmation Window](https://github.com/user-attachments/assets/9d15dcd7-0fa7-4685-9211-70cf2e673150)
  ![Post Update Table](https://github.com/user-attachments/assets/6fa0b7cd-15ff-433a-af23-9b8597d8c97c)
    
  Update w/ Some Valid Fields:  
  Before:  
  ![image](https://github.com/user-attachments/assets/4c8c3151-0e0e-46e7-aea8-aa019ec1661d)
    
  After (Same Name, old Quantity, changed Price):  
  ![Confirmation Window](https://github.com/user-attachments/assets/7e31984f-fe75-495a-abf2-cc66b34e523c)
  ![Post Update w Changed Price](https://github.com/user-attachments/assets/f3bd7391-060a-4b66-9ed7-0d6cdbfd2d9e)

  </details>
</details>

Importing/Exporting Functionality:  
Before:  
![Pre Program Import or Export](https://github.com/user-attachments/assets/45d57447-c255-4af3-b176-909e03eede91)
 
Export:  
![Post Program Export](https://github.com/user-attachments/assets/d3dc6f91-2bfe-41aa-bead-4e1263b5d49e)


# Known Issues  
1. No sorting feature for the table
2. No search feature for the table
3. If application doesn't have permission to read/write in location of operation, an endless loop of attempting to create the "inventory.txt" file and failing before trying again will take place until program is killed.
4. When last ID is deleted, system reuses said ID in new additions. System should be iterating independent of the ID system in use.
   
# Future Improvements  
1. While sorting and searching are not in place for the table itself, I already have search enabled for the ID based on the model information and could easily modify it to be different search functions based on regex matching in 4 different fields (for each of the different columns).

2. Would love to have been able to implement a hard stop feature to cut off the program when trying to create the "inventory.txt" after either n attempts or delta m time from the start of the load function to the current attempt's time (to prevent it from running after say 10-15 seconds of trying for accounting on slow systems).
