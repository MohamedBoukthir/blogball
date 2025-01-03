export interface User {
  id: number,
  firstName: string,
  lastName: string,
  username: string,
  email: string,
  roleName: string,
  imgUrl?: string
}

export interface Category {
  id: number,
  name: string
}

export interface RegisterRequest {
  firstName: string,
  lastName: string,
  username: string,
  email: string,
  password: string,
  termsAndConditions: boolean
}

export interface LoginRequest {
  username: string,
  password: string,
}
