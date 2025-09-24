import { useQuery } from '@tanstack/react-query';
import { fetchData, ApiParams, ApiResponse } from '../services/api';

export const useTableData = (params: ApiParams) => {
  return useQuery<ApiResponse, Error>({
    queryKey: ['tableData', params],
    queryFn: () => fetchData(params),
    keepPreviousData: true, // Keep previous data while fetching new data
    staleTime: 30000, // Consider data fresh for 30 seconds
  });
};
