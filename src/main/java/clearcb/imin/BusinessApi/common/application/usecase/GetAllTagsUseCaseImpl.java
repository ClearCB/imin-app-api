package clearcb.imin.BusinessApi.common.application.usecase;

import clearcb.imin.BusinessApi.common.domain.model.Tag;
import clearcb.imin.BusinessApi.common.domain.port.input.GetAllTagsUseCase;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.common.domain.port.output.TagRepositoryPort;

import java.util.Set;

public class GetAllTagsUseCaseImpl implements GetAllTagsUseCase {

    TagRepositoryPort tagRepositoryPort;
    Logger logger;


    public GetAllTagsUseCaseImpl(TagRepositoryPort tagRepositoryPort, Logger logger) {
        this.tagRepositoryPort = tagRepositoryPort;
        this.logger = logger;
    }

    @Override
    public Set<Tag> getAllTags() {
        return tagRepositoryPort.getAllTags();
    }
}
