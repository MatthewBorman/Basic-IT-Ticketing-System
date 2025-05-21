# Basic IT Ticketing System

This project is a Java-based IT ticketing system built for university coursework. It simulates key helpdesk operations such as creating, prioritizing, editing, and filtering support tickets.

## Features

- Custom-built priority queue (no Java Collections used)
- Ticket creation with:
  - Creator, Owner, Priority, Description, Tags, and Status
- Priority-based ticket processing (1 = highest)
- Edit ticket owner or priority by ID
- Filter tickets by tag or priority
- Status tracking: Open, In Progress, Resolved
- JavaFX GUI for interaction (non-assessed)

## Structure

- `Main.java` – Application entry point and GUI logic
- `Ticket.java` – Ticket class model
- `PriorityQueue.java` – Custom queue for ticket ordering

## How to Run

1. Clone the repo
2. Open the project in your Java IDE (e.g. IntelliJ or Eclipse)
3. Run `Main.java`
4. Use the GUI to create and manage tickets

## Coursework Context

Created for a Data Structures and Operating Systems module. Designed under constraints that prohibited the use of built-in Java Collections to reinforce understanding of custom data structure design.

## Topics Demonstrated

- Manual array-based data structures
- OOP and modular class design
- JavaFX user interfaces
- Basic algorithm efficiency (O(n), O(1), etc.)

## Future Improvements

- Persistent storage using JSON or text files
- Reordering queue after priority edits
- GUI ticket selection instead of manual ID input

## Author

Matthew Borman – [GitHub](https://github.com/MatthewBorman)
