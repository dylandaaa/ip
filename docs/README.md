# Wheezy

A simple task-tracking chatbot with a friendly UI. Use it to add todos, events, and deadlines, mark tasks done, search, and more.

## Getting Started

- Build and run with Gradle:
  - Windows PowerShell/CMD: `gradlew run`
  - macOS/Linux: `./gradlew run`
- The JavaFX window will open titled "Wheezy". Type commands into the input field and press Enter.

Note: Wheezy’s task-related commands use natural text (no prefixes) and support simple options like `/by`, `/from`, `/to`, and `/priority`.

## Date Format

- Dates should be in `yyyy-mm-dd` format, e.g., `2025-10-09`.

## Commands

- `list`
  - Shows all tasks.
- `todo DESCRIPTION [/priority high|medium|low]`
  - Adds a todo. Example: `todo read book /priority high`.
- `deadline DESCRIPTION /by DATE [/priority high|medium|low]`
  - Adds a deadline. Example: `deadline finish project /by 2025-12-01 /priority low`.
- `event DESCRIPTION /from DATE /to DATE [/priority high|medium|low]`
  - Adds an event. Example: `event burger party /from 2025-10-08 /to 2025-10-09`.
- `mark INDEX`
  - Marks a task as done. Example: `mark 2`.
- `unmark INDEX`
  - Marks a task as not done. Example: `unmark 2`.
- `delete INDEX`
  - Deletes a task. Example: `delete 3`.
- `find KEYWORDS`
  - Finds tasks containing the keywords. Example: `find book` or `find project report`.
- `bye` (or `exit`)
  - Exits Wheezy.

## Tips

- You can combine multi-word descriptions naturally, e.g., `todo buy groceries for dinner`.
- Optional `/priority` can be attached to `todo`, `deadline`, and `event`.

## Troubleshooting

- If you see a date format error, ensure your date is `YYYY-MM-DD`.