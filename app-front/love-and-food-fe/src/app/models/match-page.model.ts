import { MatchDTO } from "../dto/match.dto";

export class MatchPage {

    constructor(
        public content: MatchDTO[],
        public totalPages: number,
        public totalElements: number,
        public size: number,
    ) {}

}