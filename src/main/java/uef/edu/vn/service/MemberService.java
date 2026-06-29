/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.Member;
import uef.edu.vn.repository.MemberRepository;

/**
 *
 * @author minhq
 */
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(
            MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(int id) {
        return memberRepository.findById(id);
    }

    public Member save(Member member) {

        memberRepository.save(member);

        return member;
    }

    public boolean update(Member member) {

        memberRepository.update(member);

        return true;
    }

    public boolean delete(int id) {

        memberRepository.delete(id);

        return true;
    }

    public List<Member> searchByName(
            String keyword) {

        return memberRepository.searchByName(keyword);
    }

    public long count() {
        return memberRepository.count();
    }
}
