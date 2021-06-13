import { UserDTO } from "../dto/user.dto";

export class UserPage {

    constructor(
        public content: UserDTO[],
        public totalPages: number,
        public totalElements: number,
        public size: number,
    ) {}

}