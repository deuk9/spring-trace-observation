package org.example.kafkasample.entity;

import org.example.kafkasample.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class MemberTeamRelationshipTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    void testTeamMemberRelationship() {
        // Create a team
        Team team = new Team("Development Team");
        teamRepository.save(team);

        // Create members and add to team
        Member member1 = new Member("John Doe", "john@example.com");
        Member member2 = new Member("Jane Smith", "jane@example.com");

        // Use the helper method to establish bidirectional relationship
        team.addMember(member1);
        team.addMember(member2);

        memberRepository.save(member1);
        memberRepository.save(member2);
        teamRepository.save(team);

        // Clear persistence context to force a fresh read from the database
        teamRepository.flush();
        memberRepository.flush();

        // Test finding team with members
        Team foundTeam = teamRepository.findById(team.getId()).orElse(null);
        assertNotNull(foundTeam);
        assertEquals(2, foundTeam.getMembers().size());

        // Test finding members by team
        List<Member> teamMembers = memberRepository.findByTeamId(team.getId());
        assertEquals(2, teamMembers.size());

        // Test changing a member's team
        Member member3 = new Member("Bob Johnson", "bob@example.com");
        memberRepository.save(member3);

        member3.changeTeam(team);
        memberRepository.save(member3);

        teamMembers = memberRepository.findByTeamId(team.getId());
        assertEquals(3, teamMembers.size());

        // Test removing a member from a team
        member1.changeTeam(null);
        memberRepository.save(member1);

        teamMembers = memberRepository.findByTeamId(team.getId());
        assertEquals(2, teamMembers.size());

        // Test deleting a team (should not delete members)
        // First, manually set team reference to null for all members
        List<Member> membersToUpdate = memberRepository.findByTeamId(team.getId());
        for (Member member : membersToUpdate) {
            member.setTeam(null);
            memberRepository.save(member);
        }

        // Now delete the team
        teamRepository.delete(team);

        List<Member> allMembers = memberRepository.findAll();
        assertEquals(3, allMembers.size());

        // All members should now have null team
        for (Member member : allMembers) {
            assertNull(member.getTeam());
        }
    }
}
