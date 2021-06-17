import { UserMVPDTO } from "../dto/user-mvp.dto";

export class UserPage {

    constructor(
        public content: UserMVPDTO[],
        public totalPages: number,
        public totalElements: number,
        public size: number,
    ) {}

}