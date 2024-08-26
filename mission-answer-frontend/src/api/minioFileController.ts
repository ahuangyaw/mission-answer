// @ts-ignore
/* eslint-disable */
import request from '@/request';

/** delete PUT /api/minio/delete */
export async function deleteUsingPut(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteUsingPUTParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/minio/delete', {
    method: 'PUT',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** download GET /api/minio/download */
export async function downloadUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.downloadUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<any>('/api/minio/download', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** downloadFile GET /api/minio/download2 */
export async function downloadFileUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.downloadFileUsingGETParams,
  options?: { [key: string]: any },
) {
  const { objectName: param0, ...queryParams } = params;
  return request<API.BaseResponseInputStreamResource_>('/api/minio/download2', {
    method: 'GET',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** getHttpUrl GET /api/minio/getHttpUrl */
export async function getHttpUrlUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getHttpUrlUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseString_>('/api/minio/getHttpUrl', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** list GET /api/minio/list */
export async function listUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListString_>('/api/minio/list', {
    method: 'GET',
    ...(options || {}),
  });
}

/** minioUploadFile POST /api/minio/upload */
export async function minioUploadFileUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.minioUploadFileUsingPOSTParams,
  body: {},
  file?: File,
  options?: { [key: string]: any },
) {
  const formData = new FormData();

  if (file) {
    formData.append('file', file);
  }

  Object.keys(body).forEach((ele) => {
    const item = (body as any)[ele];

    if (item !== undefined && item !== null) {
      if (typeof item === 'object' && !(item instanceof File)) {
        if (item instanceof Array) {
          item.forEach((f) => formData.append(ele, f || ''));
        } else {
          formData.append(ele, JSON.stringify(item));
        }
      } else {
        formData.append(ele, item);
      }
    }
  });

  return request<API.BaseResponseString_>('/api/minio/upload', {
    method: 'POST',
    params: {
      ...params,
    },
    data: formData,
    // @ts-ignore
    requestType: 'form',
    ...(options || {}),
  });
}

/** upload POST /api/minio/upload2 */
export async function uploadUsingPost(body: {}, file?: File, options?: { [key: string]: any }) {
  const formData = new FormData();

  if (file) {
    formData.append('file', file);
  }

  Object.keys(body).forEach((ele) => {
    const item = (body as any)[ele];

    if (item !== undefined && item !== null) {
      if (typeof item === 'object' && !(item instanceof File)) {
        if (item instanceof Array) {
          item.forEach((f) => formData.append(ele, f || ''));
        } else {
          formData.append(ele, JSON.stringify(item));
        }
      } else {
        formData.append(ele, item);
      }
    }
  });

  return request<API.BaseResponseString_>('/api/minio/upload2', {
    method: 'POST',
    data: formData,
    // @ts-ignore
    requestType: 'form',
    ...(options || {}),
  });
}
