package com.example.smspr2.mapper;

import com.example.smspr2.dto.DefaultDto;
import com.example.smspr2.dto.TbpostcmtDto;

import java.util.List;

public interface TbpostcmtMapper {
    TbpostcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param);//0개 or 1개
    List<TbpostcmtDto.DetailResDto> list(TbpostcmtDto.ListReqDto param);
    List<TbpostcmtDto.DetailResDto> pagedList(TbpostcmtDto.PagedListReqDto param);
    List<TbpostcmtDto.DetailResDto> scrollList(TbpostcmtDto.ScrollListReqDto param);
    int pagedListCount(TbpostcmtDto.PagedListReqDto param);
}
