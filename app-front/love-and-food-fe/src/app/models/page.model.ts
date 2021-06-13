export interface PageObject<T> {
  content: Array<T>,
  totalElements: number,
  last: boolean,
  totalPages: number,
  size: number,
  number: number,
  numberOfElements: number,
  first: boolean,
  empty: boolean,
  pageNumber: number,
  pageSize: number
}
