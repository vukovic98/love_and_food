import { AlarmModel } from "./alarm.model";

export class AlarmPage {

    constructor(
        public content: AlarmModel[],
        public totalPages: number,
        public totalElements: number,
        public size: number,
    ) {}

}