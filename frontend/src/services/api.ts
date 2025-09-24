export interface DataItem {
  id: number;
  name: string;
  status: string;
  description: string;
  delta: number;
  createdOn: string;
}

export interface ApiResponse {
  data: DataItem[];
  totalCount: number;
  currentPage: number;
  totalPages: number;
  hasNext: boolean;
  hasPrevious: boolean;
}

export interface ApiParams {
  page?: number;
  size?: number;
  search?: string;
  status?: string;
  sortBy?: string;
  sortOrder?: 'asc' | 'desc';
}

const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export const fetchData = async (params: ApiParams = {}): Promise<ApiResponse> => {
  const queryParams = new URLSearchParams();
  
  // Default values
  queryParams.append('page', (params.page || 1).toString());
  queryParams.append('size', (params.size || 20).toString());
  
  if (params.search) {
    queryParams.append('search', params.search);
  }
  
  if (params.status) {
    queryParams.append('status', params.status);
  }
  
  if (params.sortBy) {
    queryParams.append('sortBy', params.sortBy);
  }
  
  if (params.sortOrder) {
    queryParams.append('sortOrder', params.sortOrder);
  }

  const response = await fetch(`${API_BASE_URL}/api/data?${queryParams.toString()}`);
  
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  
  return response.json();
};