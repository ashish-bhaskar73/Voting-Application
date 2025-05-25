Voting Application
A Spring Boot-based Voting Application that allows voters to vote for candidates, tracks votes, and generates election results.

📦 Technologies Used
-> Java (Spring Boot)
-> Hibernate / JPA
-> MySQL 
-> Jackson for JSON Serialization
-> Maven or Gradle

📦 Project Structure
The application includes the following main entities:

Candidate: Represents a candidate in the election.
Voter: Represents a person who can vote.
Vote: Represents a vote cast by a voter for a candidate.
ElectionResult: Stores the total votes and winner.

🧱 Entity Relationship Diagram
+-----------+          +--------+         +-------------+
|  Voter    |◄────────▶|  Vote  |◄───────▶|  Candidate  |
+-----------+          +--------+         +-------------+
| id        | 1      1 | id     |      *  | id          |
| name      |          |        |         | name        |
| email     |          |        |         | party       |
| hasVoted  |          |        |         | voteCount   |
+-----------+          +--------+         +-------------+
                            ▲
                            |
                            | 1
                            |
                    +------------------+
                    |  ElectionResult  |
                    +------------------+
                    | id               |
                    | electionName     |
                    | totalVotes       |
                    | winner_id        |
                    +------------------+




📬 API Endpoints
Example endpoints could include:


| Method | Endpoint    | Description           |
| ------ | ----------- | --------------------- |
| GET    | /candidates | List all candidates   |
| POST   | /candidates | Register a candidate  |
| POST   | /voters     | Register a voter      |
| POST   | /vote       | Cast a vote           |
| GET    | /results    | View election results |



📌 Notes
Ensure each voter can vote only once.

Votes are tied directly to a candidate.

Vote counts are managed automatically via logic in the service layer.
