import { TestAnswerDTO } from "./test-answer.dto";

export interface UserDTO {
    name: string;
    surname: string;
    email: string;
    password: string;
    dateOfBirth: string;
    gender: string;
    sexualOrientation: string;
    education: string;
    income: string;
    religion: string;
    children: string;
    desiredRelationship: string;
    location: string;
    alchocol: boolean;
    smoking: boolean;
    testAnswers: TestAnswerDTO[]
  }