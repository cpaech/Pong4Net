# Networking Messages Documentation

This document outlines the structure and details of networking messages used in the application. Each message has a unique ID(byte), an assignment to either the client or server, and a predefined structure.

## Message Template

### CreateAccount
- **ID**: `0`
- **Assigned To**: `Client`
- **Description**: Requests a ID from the server with which the client can identify itself with subsequent calls when nescessary. This ID is just a random int. The response is a message if type `1`

#### Structure
No structure
---

### AssignAccountID
- **ID**: `1`
- **Assigned To**: `Server`
- **Description**: Response to RequestAccount, returning the int ID which will identify the client in matches or on the leaderboard.

#### Structure
| Field Name      | Type          | Description                          |
|------------------|---------------|--------------------------------------|
| `identifier`    | `ID`    | Description of the field.            |

---

### SetBackgroundColor
- **ID**: `2`
- **Assigned To**: `Server`
- **Description**: Set the background/clear color of the client 

#### Structure
| Field Name      | Type          | Description                          |
|------------------|---------------|--------------------------------------|
| `red value`| `byte`    | Red component of RGB color
| `green value`| `byte`    | Green component of RGB color
| `blue value`| `byte`    | Blue component of RGB color

---

### MessageTest
- **ID**: `3`
- **Assigned To**: `Client` and `Server`
- **Description**: Used to send a text message to the receiving end 

#### Structure
| Field Name      | Type          | Description                          |
|------------------|---------------|--------------------------------------|
| `length`    | `byte`    | length of the message (max 255 characters) **This field is hidden and can't be accessed**         |
| `message`    | `string`    | Message string which's length is determined by the previous data entry. **Encoded in ASCII** |

---
