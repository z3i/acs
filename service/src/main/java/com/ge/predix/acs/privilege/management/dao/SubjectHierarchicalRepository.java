package com.ge.predix.acs.privilege.management.dao;

import java.util.Set;

import com.ge.predix.acs.model.Attribute;
import com.ge.predix.acs.zone.management.dao.ZoneEntity;

public interface SubjectHierarchicalRepository {
    SubjectEntity getSubjectWithInheritedAttributesForScopes(final ZoneEntity zone, final String subjectIdentifier,
                                                             final Set<Attribute> scopes);

    SubjectEntity getSubjectWithInheritedAttributes(final ZoneEntity zone,
            final String subjectIdentifier);

    Set<String> getSubjectEntityAndDescendantsIds(final SubjectEntity entity); 
    int getVersion();
    void setVersion(final int version);
}