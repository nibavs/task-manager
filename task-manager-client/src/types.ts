export interface Task {
  id: number
  title: string
  description: string
  status: string
  createdAt: string
}

export interface TaskRequest {
  title: string
  description: string
  status: string
}
