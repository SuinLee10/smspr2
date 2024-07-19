package com.example.smspr2.service.impl;

import com.example.smspr2.domain.Tbpost;
import com.example.smspr2.dto.TbpostDto;
import com.example.smspr2.mapper.TbpostMapper;
import com.example.smspr2.repository.TbpostRepository;
import com.example.smspr2.service.TbpostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostServiceImpl implements TbpostService {
    TbpostRepository tbpostRepository;
    TbpostMapper tbpostMapper;

    public TbpostServiceImpl(
            TbpostRepository tbpostRepository,
            TbpostMapper tbpostMapper) {
        this.tbpostRepository = tbpostRepository;
        this.tbpostMapper = tbpostMapper;
    }

    @Override
    public TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param) {
        return tbpostRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbpostDto.CreateResDto update(TbpostDto.UpdateReqDto param) {
        Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if (param.getTitle() != null) {
            tbpost.setTitle(param.getTitle());
        }
        if (param.getAuthor() != null) {
            tbpost.setAuthor(param.getAuthor());
        }
        if (param.getContent() != null) {
            tbpost.setContent(param.getContent());
        }
        tbpostRepository.save(tbpost);
        return tbpost.toCreateResDto();
    }

    @Override
    public TbpostDto.SelectResDto detail(TbpostDto.SelectReqDto param) {
        /*Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        TbpostDto.SelectResDto selectResDto = TbpostDto.SelectResDto.builder()
                .id(tbpost.getId())
                .createdAt(tbpost.getCreatedAt() + "")
                .deleted(tbpost.getDeleted())
                .title(tbpost.getTitle())
                .author(tbpost.getAuthor())
                .content(tbpost.getContent())
                .build();
        //xml에서 저장한 정보를 dto에 컬럼 명과 필드 명을 일치시켜서 가져왔음
        //mapper한테 => 자동으로 담아줌
        */
        TbpostDto.SelectResDto selectResDto = tbpostMapper.detail(param);
        if (selectResDto == null) {
            throw new RuntimeException("no data");
        }
        return selectResDto;
    }

    @Override
    public List<TbpostDto.SelectResDto> list(TbpostDto.ListReqDto param) {
        List<TbpostDto.SelectResDto> list = tbpostMapper.list(param);
        List<TbpostDto.SelectResDto> newList = new ArrayList<>();
        for (TbpostDto.SelectResDto each : list) {
            newList.add(detail(TbpostDto.SelectReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }

    @Override
    public TbpostDto.PagedListResDto pagedList(TbpostDto.PagedListReqDto param) {
        String orderby = param.getOrderby();
        if (orderby == null || orderby.isEmpty()) {
            orderby = "created_at";
        }
        String orderway = param.getOrderway();
        if (orderway == null || orderway.isEmpty()) {
            orderway = "desc";
        }
        Integer perpage = param.getPerpage();
        if (perpage == null) {
            perpage = 10;
        }
        Integer callpage = param.getCallpage();
        if (callpage == null) {
            callpage = 1;
        }
        if (callpage < 1) {
            callpage = 1;
        }
        int listsize = tbpostMapper.pagedListCount(param);
        int pagesize = listsize / perpage;
        if (listsize % perpage > 0) {
            pagesize++;
        }
        if (callpage > pagesize) {
            callpage = pagesize;
        }
        int offset = (callpage - 1) * perpage;
        param.setOrderby(orderby);
        param.setOrderway(orderway);
        param.setOffset(offset);
        param.setPerpage(perpage);

        List<TbpostDto.SelectResDto> list = tbpostMapper.pagedList(param);
        List<TbpostDto.SelectResDto> newList = new ArrayList<>();
        for (TbpostDto.SelectResDto each : list) {
            newList.add(detail(TbpostDto.SelectReqDto.builder().id(each.getId()).build()));
        }

        TbpostDto.PagedListResDto returnVal = TbpostDto.PagedListResDto.builder()
                .callpage(callpage)
                .perpage(perpage)
                .orderby(orderby)
                .orderway(orderway)
                .listsize(listsize)
                .pagesize(pagesize)
                .list(newList)
                .build();
        return returnVal;
    }
}