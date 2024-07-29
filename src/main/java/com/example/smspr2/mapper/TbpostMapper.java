package com.example.smspr2.mapper;

import com.example.smspr2.dto.DefaultDto;
import com.example.smspr2.dto.TbpostDto;

import java.util.List;

public interface TbpostMapper {
    TbpostDto.DetailResDto detail(DefaultDto.DetailReqDto param);//0개 or 1개
    List<TbpostDto.DetailResDto> list(TbpostDto.ListReqDto param);
    List<TbpostDto.DetailResDto> pagedList(TbpostDto.PagedListReqDto param);
    List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListReqDto param);
    int pagedListCount(TbpostDto.PagedListReqDto param);
}
